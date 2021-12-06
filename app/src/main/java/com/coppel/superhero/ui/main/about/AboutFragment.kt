package com.coppel.superhero.ui.main.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.coppel.superhero.BuildConfig
import com.coppel.superhero.R
import com.coppel.superhero.databinding.AboutFragmentBinding

class AboutFragment : Fragment() {

    private lateinit var binding: AboutFragmentBinding

    companion object {
        fun newInstance() = AboutFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.about_fragment, container, false)
        binding.tvAppVersion.text = BuildConfig.VERSION_NAME
        return binding.root
    }
}