package com.example.test.dagertest1

import android.os.Handler


class LoginModelImpl : ILoginModel{
    override fun login(username: String, password: String, listener: ILoginModel.LoginListener) {
        Handler().postDelayed({
            if (username == "johan" && password == "123"){
                listener.loginSuccess()
            }else{
                listener.loginFailed("密码错误")
            }
        },2000)
    }

}