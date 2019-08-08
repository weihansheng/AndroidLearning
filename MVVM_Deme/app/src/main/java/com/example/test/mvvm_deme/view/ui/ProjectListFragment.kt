package com.example.test.mvvm_demo.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.test.mvvm_deme.R
import com.example.test.mvvm_deme.databinding.FragmentProjectListBinding
import com.example.test.mvvm_demo.service.model.Project
import com.example.test.mvvm_demo.view.adapter.ProjectAdapter
import com.example.test.mvvm_demo.view.callback.ProjectClickCallback
import com.example.test.mvvm_demo.viewmodel.ProjectListViewModel

class ProjectListFragment : Fragment(){


    lateinit var viewModel:ProjectListViewModel
    lateinit var binding: FragmentProjectListBinding
    lateinit var adapter:ProjectAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=
            DataBindingUtil.inflate(inflater, R.layout.fragment_project_list,container,false)
        //实现listview item的点击事件
        adapter= ProjectAdapter(object : ProjectClickCallback {
            override fun onClick(project: Project) {
                Toast.makeText(context,"点击了"+ project.full_name,Toast.LENGTH_SHORT).show()
                var mActivity= activity as MainActivity
                mActivity.goToDetails(project)
            }
        })
        binding.projectList.adapter=adapter
        binding.isLoading=true
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel= ViewModelProviders.of(activity!!).get(ProjectListViewModel::class.java)
        viewModel.getProjectListObservable().observe(this,object :Observer<List<Project>>{
            override fun onChanged(t: List<Project>) {
                binding.isLoading=false
                Log.d("test","getproject size:"+t.size)
                adapter.setList(t)
            }
        })
    }



}