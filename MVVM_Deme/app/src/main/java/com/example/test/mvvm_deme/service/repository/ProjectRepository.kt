package com.example.test.mvvm_demo.service.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test.mvvm_demo.service.model.Project
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import javax.xml.datatype.DatatypeConstants.SECONDS
import okhttp3.OkHttpClient


class ProjectRepository{
    var token = "593f479f3993d70aca46e46cd53e086fea8a05bf"
    var gitHubService:GitHubService
    init {
        //声明日志类
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        //设定日志级别
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        //自定义OkHttpClient
        val okHttpClient = OkHttpClient.Builder()
        //添加拦截器
        okHttpClient.addInterceptor(httpLoggingInterceptor)


        var retrofit=Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        gitHubService=retrofit.create(GitHubService::class.java)
    }
    //线程安全的懒汉式单例
    companion object {
        private var instance: ProjectRepository? = null
            get() {
                if (field == null) {
                    field = ProjectRepository()
                }
                return field
            }
        @Synchronized
        fun get(): ProjectRepository{
            return instance!!
        }
    }

    fun getProjectList(userId:String): LiveData<List<Project>> {
        var data:MutableLiveData<List<Project>> = MutableLiveData<List<Project>>()
        gitHubService.getProjectList(userId).enqueue(object : Callback<List<Project>>{
            override fun onFailure(call: Call<List<Project>>?, t: Throwable?) {
                Log.d("getproject","----  failed getProjectList  "+t.toString())
            }

            override fun onResponse(call: Call<List<Project>>?, response: Response<List<Project>>?) {
                if (response?.body()==null)
                    return
                data.value=response?.body()
                Log.d("getproject","---- success:"+data.value?.size +" "+ data.value?.get(0)?.full_name)
            }
        })
        return data
    }
    fun getProject(userId:String, projectName: String):LiveData<Project>{
        var data= MutableLiveData<Project>()
        gitHubService.getProjectDetails(userId,projectName).enqueue(object : Callback<Project>{
            override fun onFailure(call: Call<Project>?, t: Throwable?) {
                Log.d("getproject","---- failed getProjectDetails  "+t.toString())
            }

            override fun onResponse(call: Call<Project>?, response: Response<Project>?) {
                data.value=response?.body()
                Log.d("http","---- success:")
            }
        })
        return data
    }

}