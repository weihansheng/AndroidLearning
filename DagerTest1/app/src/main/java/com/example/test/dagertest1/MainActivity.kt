package com.example.test.dagertest1

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ILoginView{
    @Inject
    lateinit var loginPersenter:LoginPersenter

    lateinit var progressDialog:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var etPassword =findViewById<EditText>(R.id.et_pass)
        var etUsename=findViewById<EditText>(R.id.et_user)
        DaggerLoginPersenterCompoent.builder().loginPersenterModule(LoginPersenterModule(this)) .build().inject(this)
        findViewById<Button>(R.id.btn_login).setOnClickListener {
            loginPersenter.login(etUsename.text.toString(),etPassword.text.toString())
        }
    }
    override fun showLoading() {
        progressDialog= ProgressDialog.show(this, "正在登录", "正在登录，请稍等。。。", true, false)
    }

    override fun hideLoading() {
        progressDialog.dismiss()
    }

    override fun showError(msg: String) {
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
    }

    override fun showSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
    }
}
