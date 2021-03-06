package com.coppel.superhero.ui.main.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.coppel.superhero.R
import com.coppel.superhero.adapters.HeroesAdapter
import com.coppel.superhero.databinding.HeroListFragmentBinding
import com.coppel.superhero.enums.ApiServiceStatus
import com.coppel.superhero.ui.main.MainFragmentDirections
import com.coppel.superhero.viewModels.HeroViewModel
import com.coppel.superhero.viewModels.HeroViewModelFactory


class HeroListFragment : Fragment(), SearchView.OnQueryTextListener, Toolbar.OnMenuItemClickListener {

    private lateinit var heroViewModelFactory: HeroViewModelFactory
    private lateinit var binding: HeroListFragmentBinding
    private lateinit var homeViewModel: HeroViewModel

    companion object {
        fun newInstance() = HeroListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.hero_list_fragment, container, false)
        binding.lifecycleOwner = this
        binding.toolbarMain.setOnMenuItemClickListener(this)

        heroViewModelFactory = HeroViewModelFactory(requireActivity().application)
        homeViewModel = ViewModelProvider(this, heroViewModelFactory).get(HeroViewModel::class.java)
        binding.viewModel = homeViewModel

        inflateSearchView()

        val adapter = HeroesAdapter(HeroesAdapter.OnClickListener{
            homeViewModel.displaySelectedHero(it)
        })

        binding.rvHeroList.adapter = adapter
        binding.swipeList.setOnRefreshListener {
            refreshDataList()
        }

        homeViewModel.heroList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        homeViewModel.selectedHero.observe(viewLifecycleOwner, Observer { hero ->
            if(hero != null) {
                val action = MainFragmentDirections.actionHomeFragmentToDetailFragment(hero)
                requireActivity().findNavController(R.id.nav_host).navigate(action)
                homeViewModel.displaySelectedHeroComplete()
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshDataList()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        homeViewModel.fetchHeroesList(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        Log.e(javaClass.simpleName, "---item_menu---> $item")
        when(item!!.itemId) {
            R.id.action_about -> {
                requireActivity().findNavController(R.id.nav_host).navigate(R.id.action_homeFragment_to_aboutFragment)
                return true
            }
        }
        return true
    }

    private fun refreshDataList() {
        homeViewModel.fetchHeroesList("man")
        homeViewModel.heroStatus.observe(viewLifecycleOwner, Observer { apiStatus ->
            when (apiStatus) {
                ApiServiceStatus.DONE -> {
                    if (binding.swipeList.isRefreshing) {
                        binding.swipeList.isRefreshing = false
                    }
                }
                ApiServiceStatus.NO_INTERNET_CONNECTION -> {
                    binding.imgLoadingStatus.visibility = View.VISIBLE
                    binding.imgLoadingStatus.setImageResource(R.drawable.ic_signal_wifi_bad)
                    binding.swipeList.isRefreshing = false
                }
                ApiServiceStatus.LOADING -> {
                    if (!binding.swipeList.isRefreshing) {
                        binding.swipeList.isRefreshing = true
                    }
                }
                else -> {
                    if (binding.swipeList.isRefreshing) {
                        binding.swipeList.isRefreshing = false
                    }
                }
            }
        })
    }

    private fun inflateSearchView() {
        val toolbar = binding.toolbarMain
        val searchManager = requireContext().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = toolbar.menu.findItem(R.id.action_search).actionView as SearchView

        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            setOnQueryTextListener(this@HeroListFragment)
            setIconifiedByDefault(true)
            isSubmitButtonEnabled = false
            isIconified = false
        }
    }
}