package com.coppel.superhero.ui.main.detail

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.coppel.superhero.R
import com.coppel.superhero.databinding.DetailFragmentBinding
import com.coppel.superhero.models.Hero
import com.coppel.superhero.ui.main.MainFragment
import com.coppel.superhero.ui.main.MainFragmentDirections
import com.coppel.superhero.viewModels.DetailViewModel
import com.coppel.superhero.viewModels.DetailViewModelFactory

class DetailFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    lateinit var binding: DetailFragmentBinding
    lateinit var detailViewModel: DetailViewModel
    lateinit var application: Application
    lateinit var superHero: Hero

    companion object {
        fun newInstance() = DetailFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        superHero = DetailFragmentArgs.fromBundle(requireArguments()).hero
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        binding.lifecycleOwner = this

        application = requireActivity().application
        val viewModelFactory  = DetailViewModelFactory(superHero, application)
        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        detailViewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        binding.ctbDetail.title = superHero.name

        binding.tbDetail.setNavigationOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToHomeFragment()
            requireActivity().findNavController(R.id.nav_host).navigate(action)
        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    val action = DetailFragmentDirections.actionDetailFragmentToHomeFragment()
                    requireActivity().findNavController(R.id.nav_host).navigate(action)
                }
            })
        //inflateMenu()
        return binding.root
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return true
    }

    private fun inflateMenu() {
        binding.tbDetail.setOnMenuItemClickListener(this@DetailFragment)
    }
}