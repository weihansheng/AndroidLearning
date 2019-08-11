package com.example.test.dagertest1


class LoginPersenter (loginView: ILoginView) {
    var mLoginView:ILoginView
    var mLoginModel: ILoginModel
    init {
        mLoginView=loginView
        mLoginModel=LoginModelImpl()
    }
    fun login(username: String, password: String){
        mLoginView.showLoading()
        mLoginModel.login(username,password, object : ILoginModel.LoginListener{
            override fun loginSuccess() {
                mLoginView.hideLoading()
                mLoginView.showSuccess()
            }

            override fun loginFailed(msg:String) {
                mLoginView.hideLoading()
                mLoginView.showError(msg)
            }

        })
    }

}