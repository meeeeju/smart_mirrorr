package com.example.yanadu.ui.extra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yanadu.R
import kotlinx.android.synthetic.main.activity_extra.*
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*


class ExtraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extra)

        card_healthy.setOnClickListener{
            val intent = Intent(this, HealthyActivity::class.java)
            startActivity(intent)
        }

        card_fun.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }


    }




}