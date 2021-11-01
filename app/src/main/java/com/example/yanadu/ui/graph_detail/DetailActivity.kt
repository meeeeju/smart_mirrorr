package com.example.yanadu.ui.graph_detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.example.yanadu.R
import com.example.yanadu.ui.MainActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.CompoundButton;

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        btn_drug.setOnClickListener {
            Toast.makeText(this@DetailActivity,"Proviotics(2021/06/06~) + Vitamin C(2018/03/24~)",Toast.LENGTH_LONG).show()

        }

        swch_wd.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked==true){
                img_weekgraph.setImageResource(R.drawable.daygraph)
            }
            else{
                img_weekgraph.setImageResource(R.drawable.graph)

            }
        }


    }
}


