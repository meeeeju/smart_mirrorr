package com.example.yanadu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yanadu.R;

import java.util.Random;

public class string_random extends AppCompatActivity {

    Button btn;
    TextView tv;
    String option[]={"hi","bi","i","want","to","sleep"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_random);

        btn=(Button) findViewById(R.id.btn);
        tv=(TextView) findViewById(R.id.tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random=new Random();
                final int rando=random.nextInt(4);
                tv.setText(option[rando]);

            }
        });
    }
}