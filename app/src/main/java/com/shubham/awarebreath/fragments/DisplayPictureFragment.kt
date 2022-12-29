package com.shubham.awarebreath.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.shubham.awarebreath.R
import com.shubham.awarebreath.databinding.FragmentDisplayPictureBinding
import com.shubham.awarebreath.viewModel.SharedViewModel

class DisplayPictureFragment : Fragment() {

    lateinit var binding: FragmentDisplayPictureBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDisplayPictureBinding.inflate(inflater, container, false)
//        val viewModel = ViewModelProvider(this, CreateMeditationSessionFragmentViewModelFactory(
//            requireContext())
//        )[CreateMeditationSessionFragmentViewModel::class.java]
        binding.lifecycleOwner = this



        return binding.root

    }

}