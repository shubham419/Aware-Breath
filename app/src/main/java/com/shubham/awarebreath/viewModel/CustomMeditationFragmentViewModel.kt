package com.shubham.awarebreath.viewModel

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.awarebreath.database.BreathDataBase
import com.shubham.awarebreath.database.MeditationSessionData
import com.shubham.awarebreath.repository.MeditationSessionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomMeditationFragmentViewModel(context: Context) : ViewModel() {

    private val repository: MeditationSessionRepository
    private val dialogBox: AlertDialog.Builder

    lateinit var data: LiveData<List<MeditationSessionData>>

    init {
        val dao = BreathDataBase.getBreathDatabase(context).meditationSessionDataDao()
        dialogBox = AlertDialog.Builder(context)
        repository = MeditationSessionRepository(dao)
        loadData()
    }

    private fun loadData() {
        data = repository.getMeditationSessionData()
    }

    fun deleteById(id: Int): Boolean {
        dialogBox.setTitle("Alert").setMessage("Are you sure you want to delete")
        dialogBox.setPositiveButton(
            "yes"
        ) { _, _ ->
            viewModelScope.launch(Dispatchers.IO) {
                repository.deleteById(id)
                loadData()
            }
        }
            .setNegativeButton("no", null)
            .show()
        return true
    }

}