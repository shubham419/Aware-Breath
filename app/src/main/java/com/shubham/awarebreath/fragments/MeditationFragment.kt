package com.shubham.awarebreath.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shubham.awarebreath.R
import com.shubham.awarebreath.databinding.FragmentMeditationBinding
import com.shubham.awarebreath.viewModel.MeditationFragmentViewModel
import com.shubham.awarebreath.viewModel.SharedViewModel


class MeditationFragment : Fragment() {

    private lateinit var binding: FragmentMeditationBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var music1: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeditationBinding.inflate(inflater, container, false)
        activity?.findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation_view)?.visibility =
            View.GONE

        val viewModel = ViewModelProvider(this)[MeditationFragmentViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val voice: Boolean = sharedViewModel.animationData.value!!.voice
        music1 = if (sharedViewModel.animationData.value!!.music) MediaPlayer.create(
            activity,
            R.raw.music1
        ) else MediaPlayer.create(activity, R.raw.music2)
        music1.setVolume(0.3F, 0.3F)
        music1.isLooping = true

        sharedViewModel.animationData.observe(viewLifecycleOwner, Observer { it ->
            viewModel.startTimer(it)
        })

        viewModel.hold.observe(viewLifecycleOwner) { it ->
            if (it) {
                mediaPlayer = if (voice) MediaPlayer.create(
                    activity,
                    R.raw.alex_hold
                ) else MediaPlayer.create(activity, R.raw.hold_voice)
            } else {
                mediaPlayer = if (voice) MediaPlayer.create(
                    activity,
                    R.raw.alex_intro_voice
                ) else MediaPlayer.create(activity, R.raw.lubina_intro_voice)
            }
            mediaPlayer.start()
        }

        viewModel.music.observe(viewLifecycleOwner) {
            if (it) {
                music1.start()
            } else {
                music1.stop()
            }
        }

        viewModel.state.observe(viewLifecycleOwner) { it ->
            if (it) {
                mediaPlayer = if (voice) MediaPlayer.create(
                    activity,
                    R.raw.alex_inhale
                ) else MediaPlayer.create(activity, R.raw.inhale_voice)
            } else {
                mediaPlayer = if (voice) MediaPlayer.create(
                    activity,
                    R.raw.alex_exhale
                ) else MediaPlayer.create(activity, R.raw.exhale_voice)
            }
            mediaPlayer.start()
        }

        viewModel.sessionFinished.observe(viewLifecycleOwner) {
            if (sharedViewModel.animationData.value!!.backPressHandler) {
                findNavController().navigate(R.id.action_meditationFragment_to_homeFragment)
            } else {
                findNavController().navigate(R.id.action_meditationFragment_to_customMeditationFragment)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (sharedViewModel.animationData.value!!.backPressHandler) {
                findNavController().navigate(R.id.action_meditationFragment_to_homeFragment)
            } else {
                findNavController().navigate(R.id.action_meditationFragment_to_customMeditationFragment)
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        //   music1.start()
    }

    override fun onPause() {
        super.onPause()
        music1.pause()
    }


}