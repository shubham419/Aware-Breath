package com.shubham.awarebreath.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.awarebreath.adapter.AnalyticListAdapter
import com.shubham.awarebreath.adapter.RecyclerViewAdapter
import com.shubham.awarebreath.database.AnalyticListData
import com.shubham.awarebreath.database.BreathData
import com.shubham.awarebreath.database.BreathDataBase
import com.shubham.awarebreath.database.MeditationSessionData
import com.shubham.awarebreath.repository.AnalyticRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnalyticListFragmentViewModel(context: Context) : ViewModel() {

    private val repository : AnalyticRepository
    private val adapter = AnalyticListAdapter()
    lateinit var data : LiveData<List<AnalyticListData>>

    init {
        val dao = BreathDataBase.getBreathDatabase(context).breathDataDao()
        repository = AnalyticRepository(dao)
       // putData()
        loadData()
    }

    private fun putData() {
//        val analyticListData = AnalyticListData(0,0,"successful")
//        val breathData = BreathData(0,analyticListData,0,0,0,0,0)

//        viewModelScope.launch(Dispatchers.IO) {
//            repository.insertBreathData(breathData)
//        }
    }

    private fun loadData() {
        data = repository.getAnalyticListData()
    }

}