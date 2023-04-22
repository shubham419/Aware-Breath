package com.shubham.awarebreath.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shubham.awarebreath.adapter.AnalyticListAdapter
import com.shubham.awarebreath.database.AnalyticListData
import com.shubham.awarebreath.databinding.FragmentAnalyticListBinding
import com.shubham.awarebreath.viewModel.AnalyticListFragmentViewModel
import com.shubham.awarebreath.viewModel.CustomMeditationFragmentViewModel
import com.shubham.awarebreath.viewModelFactory.AnalyticListFragmentViewModelFactory
import com.shubham.awarebreath.viewModelFactory.CustomMeditationFragmentViewModelFactory

class AnalyticListFragment : Fragment() {
    private var _binding: FragmentAnalyticListBinding? = null
    private val binding get() = _binding!!
    private  var analyticListAdapter: AnalyticListAdapter = AnalyticListAdapter()
    private lateinit var analyticList: List<AnalyticListData>
    private lateinit var viewModel: AnalyticListFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnalyticListBinding.inflate(inflater, container, false)

        viewModel =
            ViewModelProvider(this, AnalyticListFragmentViewModelFactory(requireContext()))[AnalyticListFragmentViewModel::class.java]
        viewModel.data.observe(viewLifecycleOwner) {
            analyticListAdapter.differ.submitList(it)
            analyticList=it

        }
       binding.recyclerView.adapter=analyticListAdapter


        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}