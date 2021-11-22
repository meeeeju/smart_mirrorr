package com.example.yanadu.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.yanadu.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NaviMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi_main);

        BottomNavigationView bottomnav=findViewById(R.id.bottom_navigation);
        bottomnav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                  /*  Fragment selectedFragment=null;

                    switch(item.getItemId())
                    {
                        case R.id.nav_home:
                            selectedFragment=new MainActivity();

                    }*/
                    return false;
                }


            };
}