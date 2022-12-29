package com.shubham.awarebreath.viewModelFactory

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.shubham.awarebreath.viewModel.CreateMeditationSessionFragmentViewModel

class CreateMeditationSessionFragmentViewModelFactory(val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return CreateMeditationSessionFragmentViewModel(context) as T
    }

}