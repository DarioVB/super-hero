package com.coppel.superhero.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.coppel.superhero.R
import com.coppel.superhero.databinding.MainFragmentBinding
import androidx.navigation.ui.setupWithNavController

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        setupNavBottom()
        return binding.root
    }

    private fun setupNavBottom() {
        val navController = childFragmentManager.findFragmentById(R.id.nav_main_home)
        binding.navBottom.setupWithNavController(navController = navController!!.findNavController())
    }
}