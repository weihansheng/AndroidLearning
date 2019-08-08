package com.example.test.mvvm_demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.test.mvvm_demo.service.model.Project
import com.example.test.mvvm_demo.service.repository.ProjectRepository

class ProjectListViewModel : ViewModel() {
    private var  projectListObserverable =ProjectRepository.get().getProjectList("Google")
    fun getProjectListObservable(): LiveData<List<Project>> {
        return projectListObserverable
    }

}