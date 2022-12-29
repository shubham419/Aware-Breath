package com.shubham.awarebreath.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shubham.awarebreath.database.AnalyticListData
import com.shubham.awarebreath.databinding.AnalyticListStructureBinding

class AnalyticListAdapter : RecyclerView.Adapter<AnalyticListAdapter.MyViewHolder>() {

    var list = mutableListOf<AnalyticListData>()

    fun setContentList(list: MutableList<AnalyticListData>) {
        this.list = list
        notifyDataSetChanged()
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
        holder.viewDataBinding.analyticListData = this.list[position]
    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}