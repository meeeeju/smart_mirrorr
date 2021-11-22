package com.example.yanadu.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yanadu.R;
import com.example.yanadu.data.model.CheckReturn;
import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.model.UserData;
import com.example.yanadu.data.repository.UserRepository;
import com.example.yanadu.data.request.OnGetData;

import java.util.List;


public class SignupActivity extends AppCompatActivity implements OnGetData {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button btn_signup = (Button) findViewById(R.id.btn_signup);

        EditText joinname = findViewById(R.id.ptxt_joinname);
        EditText joinid =  findViewById(R.id.ptxt_joinid);
        EditText joinpwd = findViewById(R.id.ptxt_joinpwd);
        EditText  joinmail =findViewById(R.id.ptxt_joinmail);
        EditText  joinsex =findViewById(R.id.ptxt_joinsex);
        EditText  joinsmoking = findViewById(R.id.ptxt_joinsmoking);
        EditText  joinbirth = findViewById(R.id.ptxt_joinbirth);


        UserRepository u1=new UserRepository(this);
        UserData user1 = new UserData();


        //로그인 클릭시 메인 화면으로 전환
        btn_signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Log.d("signup",joinid.getText().toString());
                user1.setId(joinid.getText().toString());
                user1.setPasswd(joinpwd.getText().toString());
                user1.setNickname(joinname.getText().toString());
                user1.setEmail(joinmail.getText().toString());
                user1.setSex(joinsex.getText().toString());
                user1.setSmoking(joinsmoking.getText().toString());
                user1.setBirth(joinbirth.getText().toString());

                u1.requestSignUp(user1);

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
        }});

    }


    @Override
    public void onGetData(ObjectData objectData) {

    }

    @Override
    public void onSendDate(ObjectData objectData) {   //그냥 회원정보 잘 갔는지 확인차원
        CheckReturn rv = (CheckReturn) objectData;
        Log.d("check",rv.getCheck()+"");
        if(rv.getCheck())
        {
            Toast.makeText(getApplicationContext(),"회원가입 성공 ", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"오류가 발생했습니다. 다시 시도해주세요! ", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onGetDataList(List<ObjectData> objectDataList) {

    }

}