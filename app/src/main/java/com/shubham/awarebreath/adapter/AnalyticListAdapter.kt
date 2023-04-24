package com.shubham.awarebreath.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shubham.awarebreath.database.AnalyticListData
import com.shubham.awarebreath.databinding.AnalyticListStructureBinding

class AnalyticListAdapter : RecyclerView.Adapter<AnalyticListAdapter.MyViewHolder>() {

//    var list = mutableListOf<AnalyticListData>()

    private val diffUtil = object : DiffUtil.ItemCallback<AnalyticListData>() {

        override fun areItemsTheSame(
            oldItem: AnalyticListData,
            newItem: AnalyticListData
        ): Boolean {
            return oldItem.nameOfMeditation == newItem.nameOfMeditation
        }

        override fun areContentsTheSame(
            oldItem: AnalyticListData,
            newItem: AnalyticListData
        ): Boolean {
            return oldItem == newItem
        }

    }
     val differ = AsyncListDiffer(this, diffUtil)
    private var analytics: List<AnalyticListData>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    inner class MyViewHolder(val viewDataBinding: AnalyticListStructureBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding =
            AnalyticListStructureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val analytic=analytics[position]
        holder.viewDataBinding.apply {
            actualDate.text= analytic.date.toString()
            actualTime.text= analytic.time.toString()
            meditationName.text= analytic.nameOfMeditation
        }

    }

    override fun getItemCount(): Int = analytics.size


}