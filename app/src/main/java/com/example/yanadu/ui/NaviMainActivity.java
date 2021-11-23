package com.example.yanadu.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.yanadu.R;
import com.example.yanadu.data.model.UserData;
import com.example.yanadu.ui.extra.FeeelActivity;
import com.example.yanadu.ui.graph_detail.WeekGraphActivity;
import com.example.yanadu.ui.mypage.MyPageActivity;
import com.example.yanadu.ui.schedule.ToDoActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NaviMainActivity extends AppCompatActivity {
    UserData user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi_main);



        BottomNavigationView bottomnav=findViewById(R.id.bottom_navigation);
        bottomnav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new RealMainActivity()).commit();



    }

    //dkdkdk
    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment=null;
                    user = (UserData) getIntent().getSerializableExtra("User");  //fragment에서 값 받는 방법

                    switch(item.getItemId())
                    {
                        case R.id.nav_home:
                            selectedFragment=new RealMainActivity();
                            break;
                        case R.id.nav_observe:
                            selectedFragment=new WeekGraphActivity();
                            break;
                        case R.id.nav_today:
                            selectedFragment=new ToDoActivity();
                            break;
                        case R.id.nav_feel:
                            selectedFragment=new FeeelActivity();
                            break;
                        case R.id.nav_mypage:
                            selectedFragment=new MyPageActivity();
                            Bundle bundle=new Bundle();   //bundle 생성해서 보내주기
                            bundle.putSerializable("User", user);
                            selectedFragment.setArguments(bundle);
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();


                    return true;
                }


            };
}