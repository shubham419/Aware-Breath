package com.shubham.awarebreath.fragments

import android.content.Context
import android.media.MediaPlayer
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shubham.awarebreath.R
import com.shubham.awarebreath.databinding.FragmentCreateMeditationSessionBinding
import com.shubham.awarebreath.viewModel.CreateMeditationSessionFragmentViewModel
import com.shubham.awarebreath.viewModel.SharedViewModel
import com.shubham.awarebreath.viewModelFactory.CreateMeditationSessionFragmentViewModelFactory


class CreateMeditationSessionFragment : Fragment() {

    lateinit var binding: FragmentCreateMeditationSessionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateMeditationSessionBinding.inflate(inflater, container, false)

        activity?.findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation_view)?.visibility = View.GONE



        val viewModel = ViewModelProvider(
            this, CreateMeditationSessionFragmentViewModelFactory(
                requireContext()
            )
        )[CreateMeditationSessionFragmentViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.visibility.observe(viewLifecycleOwner) {
            if (!it) {
                activity?.currentFocus.let { view ->
                    val imm =
                        activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(view?.windowToken, 0)
                }
            }
        }

        viewModel.voice.observe(viewLifecycleOwner) {
            if (it) {
                val introVoice: MediaPlayer = MediaPlayer.create(activity, R.raw.alex_intro_voice)
                introVoice.start()
            }
            else{
                val introVoice: MediaPlayer = MediaPlayer.create(activity, R.raw.lubina_intro_voice)
                introVoice.start()
            }
        }

        viewModel.music.observe(viewLifecycleOwner){
            if (it) {
                val music: MediaPlayer = MediaPlayer.create(activity, R.raw.music1_intro)
                music.start()
                music.setVolume(0.3F,0.3F)
            }
            else{
                val music: MediaPlayer = MediaPlayer.create(activity, R.raw.music2_intro)
                music.setVolume(0.3F,0.3F)
                music.start()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            findNavController().navigate(R.id.action_createMeditationSessionFragment_to_customMeditationFragment)
        }

        return binding.root
    }

}