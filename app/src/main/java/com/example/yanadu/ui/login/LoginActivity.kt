package com.example.yanadu.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import android.widget.Toast
=======
import android.util.Log
>>>>>>> c9923f7300d56e717770688e0d9d95ac8726c38b
import com.example.yanadu.R
import com.example.yanadu.data.request.ApiRequestFactory
import com.example.yanadu.ui.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

<<<<<<< HEAD
    val TAG: String = "Register"
    var isExistBlank = false
    var isPWSame = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //회원가입하러 가기
=======
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var api: ApiRequestFactory
        //회원가입하러 가
>>>>>>> c9923f7300d56e717770688e0d9d95ac8726c38b
        btn_gotojoin.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        //로그인 하러가기
        btn_login.setOnClickListener {
<<<<<<< HEAD
            val id = ptxt_id.text.toString()
            val pw = ptxt_pwd.text.toString()

            if(id.isEmpty() || pw.isEmpty() ){
                isExistBlank = true
                Toast.makeText(this, "입력란을 모두 채워주세요.", Toast.LENGTH_SHORT).show()

            }
            else
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

=======
            val intent = Intent(this, MainActivity::class.java)
            api = ApiRequestFactory()
            api.getData()
            startActivity(intent)
>>>>>>> c9923f7300d56e717770688e0d9d95ac8726c38b
        }


    }
}
