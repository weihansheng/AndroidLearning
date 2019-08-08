package com.example.test.mvvm_demo.service.repository

import com.example.test.mvvm_demo.service.model.Project
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {
    val HTTPS_API_GITHUB_URL:String
        get() = "https://api.github.com/";
    //retrofit注解
    //githubapi认证 通过header或者添加access_token参数  https://segmentfault.com/a/1190000015144126
    @Headers("Authorization:token 593f479f3993d70aca46e46cd53e086fea8a05bf")
    @GET("users/{user}/repos")
    fun getProjectList(@Path("user") user: String):Call<List<Project>>
//    fun getProjectList(@Path("user") user: String, @Query("access_token") token :String):Call<List<Project>>

    @Headers("User-Agent:request")
    @GET("repos/{user}/{reponame}")
    fun getProjectDetails(@Path("user") user:String,@Path("reponame") projectName:String):Call<Project>
    
}