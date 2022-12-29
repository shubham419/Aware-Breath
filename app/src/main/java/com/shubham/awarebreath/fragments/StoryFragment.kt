package com.shubham.awarebreath.fragments

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.shubham.awarebreath.R
import com.shubham.awarebreath.database.BreathData
import com.shubham.awarebreath.database.BreathDataBase
import com.shubham.awarebreath.databinding.FragmentHomeBinding
import com.shubham.awarebreath.databinding.FragmentStoryBinding
import com.shubham.awarebreath.viewModel.HomeFragmentViewModel


class StoryFragment : Fragment() {

    private lateinit var binding : FragmentStoryBinding
    private lateinit var homeFragmentViewModel: HomeFragmentViewModel
    private lateinit var database: BreathDataBase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoryBinding.inflate(inflater, container, false)

//        homeFragmentViewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]
//        database = BreathDataBase.getBreathDatabase(this.requireContext())
//        var data : BreathData? = null
//        if (homeFragmentViewModel.noOfBreath == 0) {
//            binding.saveData.visibility = View.INVISIBLE
//        }
//        binding.noOfBreath.text = homeFragmentViewModel.noOfBreath.toString()
//
//        fun setNoOfBreathText() {
//            binding.noOfBreath.text = homeFragmentViewModel.noOfBreath.toString()
//        }
//
//        binding.awareBreathBtn.setOnClickListener() {
//            if (homeFragmentViewModel.noOfBreath == 0) {
//                binding.timer.base = SystemClock.elapsedRealtime() - 0
//                binding.timer.start()
//                binding.saveData.visibility = View.VISIBLE
//            }
//            homeFragmentViewModel.breathIncrement(true)
//            setNoOfBreathText()
//        }
//
//        binding.unawareBreathBtn.setOnClickListener() {
//            homeFragmentViewModel.breathIncrement(false)
//            setNoOfBreathText()
//        }
//
//
//        binding.saveData.setOnClickListener() {
//            binding.saveData.visibility = View.INVISIBLE
//            val duration: String = binding.timer.toString()
//            binding.noOfBreath.text = duration.toString()
//            homeFragmentViewModel.noOfBreath = 0
//            binding.timer.base = SystemClock.elapsedRealtime()
//            binding.timer.stop()
//            //     setNoOfBreathText()
////            data = BreathData(0,)
////            saveData()
//            Toast.makeText(activity, "data Saved", Toast.LENGTH_SHORT).show()
//        }


        return binding.root
    }

}