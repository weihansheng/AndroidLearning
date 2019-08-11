package com.example.test.dagertest1

interface ILoginView {
    fun showLoading()
    fun hideLoading()
    fun showError(msg: String)
    fun showSuccess()
}