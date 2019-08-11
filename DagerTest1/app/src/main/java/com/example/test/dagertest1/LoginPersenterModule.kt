package com.example.test.dagertest1

import dagger.Module
import dagger.Provides

@Module
class LoginPersenterModule (activity: MainActivity){
    var loginActivity: MainActivity
    init {
        loginActivity=activity
    }
    @Provides
    fun provideLoginActivity(): MainActivity {
        return loginActivity
    }

    @Provides
    fun provideLoginPersenter(loginActivity: MainActivity): LoginPersenter {
        return LoginPersenter(loginActivity)
    }
}