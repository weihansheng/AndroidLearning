package com.example.test.dagertest1

import dagger.Component


@Component(modules = arrayOf(LoginPersenterModule::class))
interface LoginPersenterCompoent {
    fun inject(activity : MainActivity)
}