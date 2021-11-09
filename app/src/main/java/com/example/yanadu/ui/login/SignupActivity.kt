package com.example.yanadu.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.yanadu.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    val TAG: String = "Register"
    var isExistBlank = false
    var isPWSame = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        btn_signup.setOnClickListener {
            val joinname = et_join_name.text.toString()
            val joinid = et_join_id.text.toString()
            val joinpwd = et_join_pwd.text.toString()
            val joinmail = et_join_mail.text.toString()

            if(joinname.isEmpty() || joinid.isEmpty() || joinpwd.isEmpty() || joinmail.isEmpty()){
                isExistBlank = true
                Toast.makeText(this, "입력란을 모두 채워주세요.", Toast.LENGTH_SHORT).show()

            }
            else
            {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

        }
    }
}