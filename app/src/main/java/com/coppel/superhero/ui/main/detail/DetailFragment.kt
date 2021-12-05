package com.coppel.superhero.ui.main.detail

import android.app.Application
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.coppel.superhero.R
import com.coppel.superhero.databinding.DetailFragmentBinding
import com.coppel.superhero.models.Hero
import com.coppel.superhero.room.HeroesDatabase
import com.coppel.superhero.ui.main.MainFragment
import com.coppel.superhero.ui.main.MainFragmentDirections
import com.coppel.superhero.utils.Constants
import com.coppel.superhero.viewModels.DetailViewModel
import com.coppel.superhero.viewModels.DetailViewModelFactory

class DetailFragment : Fragment() {

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
        val dbFavorites = HeroesDatabase.getInstance(application).favoritesDao

        val viewModelFactory  = DetailViewModelFactory(superHero, application, dbFavorites)
        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        detailViewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        binding.tbDetail.setNavigationOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToHomeFragment(Constants.BACK_PRESSED)
            requireActivity().findNavController(R.id.nav_host).navigate(action)
        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    val action = DetailFragmentDirections.actionDetailFragmentToHomeFragment(Constants.BACK_PRESSED)
                    requireActivity().findNavController(R.id.nav_host).navigate(action)
                }
            })

        binding.fabAddFavorites.setOnClickListener {
            binding.viewModel?.addToFavourites()
        }

        detailViewModel.statusAddToFavorite.observe(viewLifecycleOwner, Observer {
            when(it) {
                true -> {
                    binding.fabAddFavorites.setImageResource(R.drawable.ic_favorite)
                    Toast.makeText(context, detailViewModel.selectedHero.value?.name + getString(R.string.info_added_to_favorites), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    binding.fabAddFavorites.setImageResource(R.drawable.ic_add_favorite)
                    Toast.makeText(context, detailViewModel.selectedHero.value?.name + getString(R.string.info_failed_to_add_to_favourite), Toast.LENGTH_SHORT).show()
                }
            }
        })

        detailViewModel.isHeroAdded.observe(viewLifecycleOwner, Observer {
            when(it) {
                true -> { binding.fabAddFavorites.setImageResource(R.drawable.ic_favorite) }
                else -> { binding.fabAddFavorites.setImageResource(R.drawable.ic_add_favorite) }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel.isHeroAdded()
    }
}