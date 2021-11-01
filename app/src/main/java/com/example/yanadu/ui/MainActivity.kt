package com.example.yanadu.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.view.GravityCompat
import com.example.yanadu.R
import com.example.yanadu.ui.extra.ExtraActivity
import com.example.yanadu.ui.extra.GameActivity
import com.example.yanadu.ui.graph_detail.DetailActivity
import com.example.yanadu.ui.mypage.MyPageActivity
import com.example.yanadu.ui.schedule.ScheduleActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{  //navigationView 상속해야함
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_navi.setOnClickListener{
            layout_drawer.openDrawer(GravityCompat.START) //START: left  //END: right
        }
        naviView.setNavigationItemSelectedListener(this)   //네비게이션 메뉴 아이템에 클릭 속성 부여 얘가 없으면 아무리 클릭해도 전환 안

        tv_makbak.setOnClickListener {
            Toast.makeText(this@MainActivity,"정상 수치입니다.",Toast.LENGTH_SHORT).show()
        }
        tv_heart.setOnClickListener {
            Toast.makeText(this@MainActivity,"정상 수치입니다.",Toast.LENGTH_SHORT).show()
        }
        tv_oxgen.setOnClickListener {
            Toast.makeText(this@MainActivity,"정상 수치입니다.",Toast.LENGTH_SHORT).show()
        }


        ib_graph.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)

        }
        iv_todo.setOnClickListener {
            val intent = Intent(this, ScheduleActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {  //네비게이션 메뉴 아이템 클릭시 수
        when(item.itemId)
        {
            R.id.main ->
            {
                Toast.makeText(applicationContext,"메인페이지",LENGTH_SHORT).show()}
            R.id.hearing ->
            {
                Toast.makeText(applicationContext,"건강분석",LENGTH_SHORT).show()
                val intent= Intent(this, DetailActivity::class.java)  //다음 화면으로이동하기 위한 인텐트 객체 생성
                startActivity(intent)
            }
            R.id.videogame ->
            {
                val intent= Intent(this, ExtraActivity::class.java)  //다음 화면으로이동하기 위한 인텐트 객체 생성
                startActivity(intent)
            }
            R.id.list ->
            {
                Toast.makeText(applicationContext,"스케줄 ",LENGTH_SHORT).show()
                val intent= Intent(this, ScheduleActivity::class.java)  //다음 화면으로이동하기 위한 인텐트 객체 생성
                startActivity(intent)
            }

            R.id.mypage ->
            {
                Toast.makeText(applicationContext,"마이페이지 ",LENGTH_SHORT).show()
                val intent= Intent(this, MyPageActivity::class.java)  //다음 화면으로이동하기 위한 인텐트 객체 생성
                startActivity(intent)
            }

        }
        layout_drawer.closeDrawers()  //네비게이션 뷰 닫됨
        return false
    }

    override fun onBackPressed() {  //뒤로 가기 누를 경
        if (layout_drawer.isDrawerOpen(GravityCompat.START))
        {
            layout_drawer.closeDrawers()
        }
        else
        {
            super.onBackPressed()   //일반 백버튼 기능 실행
        }

    }
}