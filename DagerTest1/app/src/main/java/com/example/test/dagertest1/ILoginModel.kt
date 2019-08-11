package com.example.test.dagertest1

interface ILoginModel {
    fun login(username: String, password:String, listener: LoginListener)

    interface LoginListener{
        fun loginSuccess()
        fun loginFailed(msg:String)
    }
}