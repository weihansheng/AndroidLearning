package com.example.test.mvvm_demo.view.callback

import com.example.test.mvvm_demo.service.model.Project

interface ProjectClickCallback {
    //定义listview item点击事件接口
    fun onClick(project: Project)
}