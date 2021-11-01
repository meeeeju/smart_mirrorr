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
            val joinname = ptxt_joinname.text.toString()
            val joinid = ptxt_joinid.text.toString()
            val joinpwd = ptxt_joinpwd.text.toString()
            val joinmail = ptxt_joinmail.text.toString()

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