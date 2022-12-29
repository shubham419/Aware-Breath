package com.shubham.awarebreath.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shubham.awarebreath.adapter.AnalyticListAdapter
import com.shubham.awarebreath.databinding.FragmentAnalyticListBinding
import com.shubham.awarebreath.viewModel.AnalyticListFragmentViewModel
import com.shubham.awarebreath.viewModel.CustomMeditationFragmentViewModel
import com.shubham.awarebreath.viewModelFactory.AnalyticListFragmentViewModelFactory
import com.shubham.awarebreath.viewModelFactory.CustomMeditationFragmentViewModelFactory

class AnalyticListFragment : Fragment() {

    private lateinit var binding: FragmentAnalyticListBinding
    private val adapter = AnalyticListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnalyticListBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(
            this,
            AnalyticListFragmentViewModelFactory(requireContext())
        )[AnalyticListFragmentViewModel::class.java]

        viewModel.data.observe(viewLifecycleOwner, Observer {
            it
            adapter.setContentList(it.toMutableList())
        })

        binding.recyclerView.adapter = adapter


        return binding.root
    }

}