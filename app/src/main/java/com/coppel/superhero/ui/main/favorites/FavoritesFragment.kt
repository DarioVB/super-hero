package com.coppel.superhero.ui.main.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.coppel.superhero.R
import com.coppel.superhero.databinding.FavoritesFragmentBinding

class FavoritesFragment : Fragment() {

    private lateinit var binding: FavoritesFragmentBinding

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.favorites_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }
}