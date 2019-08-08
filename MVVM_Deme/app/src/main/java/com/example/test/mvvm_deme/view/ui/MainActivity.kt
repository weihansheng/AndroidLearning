package com.example.test.mvvm_demo.view.ui

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.test.mvvm_deme.R
import com.example.test.mvvm_demo.service.model.Project
import com.example.test.mvvm_demo.service.repository.ProjectRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.INTERNET), 1)
        //可以获取到数据
        ProjectRepository.get().getProjectList("facebook")
        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction().add(R.id.fragment_container,ProjectListFragment(),"ProjectListFragment").commit()
        }
    }
    fun goToDetails(project: Project){
        supportFragmentManager.beginTransaction()
            .addToBackStack("project")
            .replace(R.id.fragment_container,ProjectFragment().forProject(project.name),"ProjectFragment")
            .commit()
    }
}
