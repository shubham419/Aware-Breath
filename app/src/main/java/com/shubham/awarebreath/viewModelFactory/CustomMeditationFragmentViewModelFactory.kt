package com.shubham.awarebreath.viewModelFactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.shubham.awarebreath.viewModel.CustomMeditationFragmentViewModel

class CustomMeditationFragmentViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return CustomMeditationFragmentViewModel(context) as T
    }
}