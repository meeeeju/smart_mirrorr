package com.example.yanadu.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yanadu.R;
import com.example.yanadu.data.model.UserData;
import com.example.yanadu.data.repository.UserRepository;


public class SignupActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button btn_signup = (Button) findViewById(R.id.btn_signup);

        EditText joinname =(EditText) findViewById(R.id.ptxt_joinname);
        EditText joinid = (EditText) findViewById(R.id.ptxt_joinid);
        EditText joinpwd = (EditText) findViewById(R.id.ptxt_joinpwd);
        EditText  joinmail =(EditText) findViewById(R.id.ptxt_joinmail);
        EditText  joinsex =(EditText) findViewById(R.id.ptxt_joinsex);
        EditText  joinsmoking =(EditText) findViewById(R.id.ptxt_joinsmoking);
        EditText  joinbirth =(EditText) findViewById(R.id.ptxt_joinbirth);


        UserData user1 = new UserData();

        user1.setId(joinname.getText().toString());
        user1.setPasswd(joinpwd.getText().toString());
        user1.setNickname(joinname.getText().toString());
        user1.setEmail(joinmail.getText().toString());
        user1.setSex(joinsex.getText().toString());
        user1.setSmoking(joinsmoking.getText().toString());
        user1.setBirth(joinbirth.getText().toString());




        //로그인 클릭시 메인 화면으로 전환
        btn_signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                UserRepository.requestSignUp(user1);

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
        }});

    }
}