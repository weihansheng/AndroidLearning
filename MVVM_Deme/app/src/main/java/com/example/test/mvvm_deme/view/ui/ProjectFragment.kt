package com.example.test.mvvm_demo.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.test.mvvm_deme.R
import com.example.test.mvvm_deme.databinding.FragmentProjectDetailsBinding
import com.example.test.mvvm_demo.service.model.Project
import com.example.test.mvvm_demo.viewmodel.ProjectViewModel

class ProjectFragment : Fragment() {
    val KEY_PROJECT_ID = "project_id"
    lateinit var viewModel:ProjectViewModel
    lateinit var binding: FragmentProjectDetailsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_project_details,container,false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = ProjectViewModel.Factory(
            activity!!.application, arguments!!.getString(KEY_PROJECT_ID)
        )
        //因为ProjectViewModel构造函数需要有参数，没有参数ViewModelProviders会报错， 使用factory将参数传进去
        viewModel= ViewModelProviders.of(activity!!,factory).get(ProjectViewModel::class.java)
//        viewModel= ViewModelProviders.of(activity!!).get(ProjectViewModel::class.java)
        var project=Project()
        project.name="johan  hahaha"
        viewModel.setProject(project)
        binding.projectViewModel=viewModel
        binding.isLoading=true
    }
    /** Creates project fragment for specific project ID  */
    fun forProject(projectID: String): ProjectFragment {
        val fragment = ProjectFragment()
        val args = Bundle()

        args.putString(KEY_PROJECT_ID, projectID)
        fragment.arguments = args

        return fragment
    }
}