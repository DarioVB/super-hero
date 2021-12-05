package com.coppel.superhero.ui.main.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.coppel.superhero.R
import com.coppel.superhero.adapters.FavoritesAdapter
import com.coppel.superhero.databinding.FavoritesFragmentBinding
import com.coppel.superhero.room.HeroesDatabase
import com.coppel.superhero.ui.main.MainFragmentDirections
import com.coppel.superhero.viewModels.FavoritesViewModel
import com.coppel.superhero.viewModels.FavoritesViewModelFactory

class FavoritesFragment : Fragment() {

    private lateinit var binding: FavoritesFragmentBinding
    private lateinit var favsViewModel : FavoritesViewModel

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.favorites_fragment, container, false)
        binding.lifecycleOwner = this

        val application = requireActivity().application
        val database = HeroesDatabase.getInstance(application).favoritesDao
        val mFavouriteViewModelFactory = FavoritesViewModelFactory(application, database)

        favsViewModel = ViewModelProvider(this, mFavouriteViewModelFactory).get(FavoritesViewModel::class.java)
        binding.viewModel = favsViewModel

        val adapter = FavoritesAdapter(FavoritesAdapter.OnClickListener{
            favsViewModel.displaySelectedHero(it)
        })

        binding.rvFavorites.adapter = adapter

        favsViewModel.favouriteHero.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        favsViewModel.selectedHero.observe(viewLifecycleOwner, Observer {
            if(it != null){
                val action = MainFragmentDirections.actionHomeFragmentToDetailFragment(it)
                requireActivity().findNavController(R.id.nav_host).navigate(action)
                favsViewModel.displaySelectedHeroComplete()
            }
        })

        return binding.root
    }
}