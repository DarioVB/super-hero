package com.coppel.superhero.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.coppel.superhero.R
import com.coppel.superhero.databinding.MainFragmentBinding
import com.coppel.superhero.ui.main.favorites.FavoritesFragmentDirections
import com.coppel.superhero.ui.main.home.HeroListFragmentDirections
import com.coppel.superhero.utils.Constants
import com.google.android.material.navigation.NavigationBarView

class MainFragment : Fragment(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.navBottom.setOnItemSelectedListener(this)
        //setupNavBottom()
        return binding.root
    }

    private fun setupNavBottom() {
        val navController = childFragmentManager.findFragmentById(R.id.nav_main_home)
        binding.navBottom.setupWithNavController(navController = navController!!.findNavController())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.title) {
            Constants.TITLE_HOME -> {
                val action = FavoritesFragmentDirections.actionFavsFragmentToHomeFragment()
                requireActivity().findNavController(R.id.nav_main_home).navigate(action)
            }
            Constants.TITLE_FAVORITES -> {
                val action = HeroListFragmentDirections.actionHomeFragmentToFavsFragment()
                requireActivity().findNavController(R.id.nav_main_home).navigate(action)
            }
        }
        return true
    }
}