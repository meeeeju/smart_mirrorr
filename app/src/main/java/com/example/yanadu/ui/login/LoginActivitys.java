package com.example.yanadu.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yanadu.R;
import com.example.yanadu.data.model.SignInForm;
import com.example.yanadu.data.repository.UserRepository;
import com.example.yanadu.data.request.UserAPI;
import com.example.yanadu.ui.MainActivity;

import static android.content.ContentValues.TAG;

public class LoginActivitys extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.yanadu.R.layout.activity_login);

        Button btn_login = (Button) findViewById(R.id.btn_login);
        EditText et_id=(EditText) findViewById(R.id.et_id);
        EditText et_pwd=(EditText) findViewById(R.id.et_pwd);

        UserRepository u=new UserRepository();


        //로그인 클릭시 메인 화면으로 전환
        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

//                SignInForm signIn = new SignInForm();
//                signIn.setId(et_id.getText().toString());
//                signIn.setPwd(et_pwd.getText().toString());
//                Log.e("test",signIn.toString()+et_id.getText().toString()+et_pwd.getText().toString());

                u.requestSignIn(et_id.getText().toString(),et_pwd.getText().toString());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }
}