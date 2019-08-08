package com.example.test.mvvm_demo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.test.mvvm_deme.R
import com.example.test.mvvm_deme.databinding.ProjectListItemBinding
import com.example.test.mvvm_demo.service.model.Project
import com.example.test.mvvm_demo.view.callback.ProjectClickCallback

class ProjectAdapter(clickCallback: ProjectClickCallback) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {
    var mClickCallback=clickCallback
    var items: List<Project>? = null
    fun setList(items:List<Project>){
        this.items=items
        //数据源改变时更新ListView数据
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        var binding=DataBindingUtil.inflate<ProjectListItemBinding>(LayoutInflater.from(parent.context),
             R.layout.project_list_item,parent,false)
        binding.callback=mClickCallback
        return ProjectViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return items?.size ?: 0;
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.binding.project= items?.get(position)
        holder.binding.executePendingBindings()
    }


    class ProjectViewHolder(binding: ProjectListItemBinding): RecyclerView.ViewHolder(binding.root) {

        var binding:ProjectListItemBinding=binding

    }
}

