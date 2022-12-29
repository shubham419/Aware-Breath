package com.shubham.awarebreath.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shubham.awarebreath.R
import com.shubham.awarebreath.adapter.RecyclerViewAdapter
import com.shubham.awarebreath.databinding.FragmentCustomMeditationBinding
import com.shubham.awarebreath.viewModel.CustomMeditationFragmentViewModel
import com.shubham.awarebreath.viewModel.SharedViewModel
import com.shubham.awarebreath.viewModelFactory.CustomMeditationFragmentViewModelFactory

class CustomMeditationFragment : Fragment() {

    lateinit var binding: FragmentCustomMeditationBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var adapter : RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomMeditationBinding.inflate(inflater, container, false)

        activity?.findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation_view)?.visibility = View.VISIBLE

        val viewModel = ViewModelProvider(
            this,
            CustomMeditationFragmentViewModelFactory(requireContext())
        )[CustomMeditationFragmentViewModel::class.java]
        adapter = RecyclerViewAdapter(sharedViewModel, viewModel)
        viewModel.data.observe(viewLifecycleOwner, Observer {
            it
            adapter.setContentList(it.toMutableList())
        })

        sharedViewModel.transition.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(R.id.action_customMeditationFragment_to_meditationFragment)
            }
        })


        binding.floatingActionButton.setOnClickListener(){
            findNavController().navigate(R.id.action_customMeditationFragment_to_createMeditationSessionFragment)
        }

        binding.recyclerView.adapter = adapter

        return binding.root
    }

}