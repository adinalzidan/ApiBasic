package com.example.apibasic.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.apibasic.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        //accept
        var argsUsername = DetailFragmentArgs.fromBundle(arguments!!).username
        val vmFactory = DetailViewModelFactory(argsUsername)

        binding.viewMOdel = ViewModelProvider(
            this, vmFactory
        ).get(DetailViewModel::class.java)

        return binding.root
    }

}