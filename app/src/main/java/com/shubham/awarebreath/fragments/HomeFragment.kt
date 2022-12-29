package com.shubham.awarebreath.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shubham.awarebreath.R
import com.shubham.awarebreath.databinding.FragmentHomeBinding
import com.shubham.awarebreath.viewModel.HomeFragmentViewModel
import com.shubham.awarebreath.viewModel.SharedViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeFragmentViewModel: HomeFragmentViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.sharedViewModel = sharedViewModel
        activity?.findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation_view)?.visibility = View.VISIBLE

        sharedViewModel.transition.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(R.id.action_homeFragment_to_meditationFragment)
            }
        })

        return binding.root
    }
}