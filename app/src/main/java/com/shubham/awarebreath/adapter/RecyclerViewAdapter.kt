package com.shubham.awarebreath.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shubham.awarebreath.database.MeditationSessionData
import com.shubham.awarebreath.databinding.RecyclerViewStructureBinding
import com.shubham.awarebreath.viewModel.CustomMeditationFragmentViewModel
import com.shubham.awarebreath.viewModel.SharedViewModel

class RecyclerViewAdapter(
    val sharedViewModel: SharedViewModel,
    val viewModel: CustomMeditationFragmentViewModel
) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var list = mutableListOf<MeditationSessionData>()
    fun setContentList(list: MutableList<MeditationSessionData>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val viewDataBinding: RecyclerViewStructureBinding) :
         RecyclerView.ViewHolder(viewDataBinding.root) {
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            RecyclerViewStructureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewDataBinding.meditationSessionData = this.list[position]
        holder.viewDataBinding.sharedViewModel = sharedViewModel
        holder.viewDataBinding.viewModel = viewModel
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

}

