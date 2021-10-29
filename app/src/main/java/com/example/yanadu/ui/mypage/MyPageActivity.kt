package com.example.yanadu.ui.mypage

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yanadu.R
import kotlinx.android.synthetic.main.activity_my_page.*

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        btn_male.setOnClickListener {btn_male.setBackgroundColor(Color.DKGRAY)}
        btn_female.setOnClickListener {btn_female.setBackgroundColor(Color.DKGRAY)}
    }

}
