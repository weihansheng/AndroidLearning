package com.example.test.mvvm_demo.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test.mvvm_demo.service.model.Project
import com.example.test.mvvm_demo.service.repository.ProjectRepository

class ProjectViewModel (application: Application,projectId:String): ViewModel(){
    var projectObservable=ProjectRepository.get().getProject("facebook",projectId)
//    var projectObservable=ProjectRepository.get().getProject("facebook","acai")
    var project: ObservableField<Project> = ObservableField()
    fun getObservableProject(): LiveData<Project> {
        return projectObservable
    }
    fun setProject(project: Project) {
        this.project.set(project)
    }

    /**
     * A creator is used to inject the project ID into the ViewModel
     */
    class Factory( application: Application, projectID: String
    ) : ViewModelProvider.NewInstanceFactory() {
        var application:Application=application
        var projectID:String=projectID

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return ProjectViewModel(application, projectID) as T
        }
    }

}