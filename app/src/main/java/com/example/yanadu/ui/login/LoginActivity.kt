package com.example.yanadu.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.yanadu.R
import com.example.yanadu.ui.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import com.example.yanadu.data.request.ApiRequestFactory

class LoginActivity : AppCompatActivity() {

    val TAG: String = "Register"
    var isExistBlank = false
    var isPWSame = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var api: ApiRequestFactory;
        //회원가입하러 가기
        btn_gotojoin.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        //로그인 하러가기
        btn_login.setOnClickListener {
            val id = et_id.text.toString()
            val pw = et_pwd.text.toString()

//            api = ApiRequestFactory()
//            api.getData()


            if(id.isEmpty() || pw.isEmpty() ){
                isExistBlank = true
                Toast.makeText(this, "입력란을 모두 채워주세요.", Toast.LENGTH_SHORT).show()

            }
            else
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }


    }
}