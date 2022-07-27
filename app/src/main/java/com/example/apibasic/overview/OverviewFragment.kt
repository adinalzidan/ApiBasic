package com.example.apibasic.overview

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.example.apibasic.databinding.FragmentOverviewBinding


class OverviewFragment : Fragment() {
    private lateinit var viewModel: OverviewViewModel
    private lateinit var binding: FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)
        binding = FragmentOverviewBinding.inflate(inflater)



        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        //add recycleview
        val viewAdapter = ItemAdapter({item->showDetail(item)})
        binding.recycleView.adapter = viewAdapter

        viewModel.item.observe(this, Observer { list ->
            viewAdapter.submitList(list)
        })


        return binding.root
    }

    fun showDetail(username: String){
        Log.d("debug","OnClick : " + username)
        //navigasi ke halaman lain
        this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(username))
    }

}