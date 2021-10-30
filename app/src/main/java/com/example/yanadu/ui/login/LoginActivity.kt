package com.example.yanadu.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.yanadu.R
import com.example.yanadu.data.request.ApiRequestFactory
import com.example.yanadu.ui.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var api: ApiRequestFactory
        //회원가입하러 가
        btn_gotojoin.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        //로그인 하러가기
        btn_login.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            api = ApiRequestFactory()
            api.getData()
            startActivity(intent)
        }


    }
}
