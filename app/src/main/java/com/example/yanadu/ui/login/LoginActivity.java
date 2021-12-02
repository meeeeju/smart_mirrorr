package com.example.yanadu.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yanadu.R;
import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.model.UserData;
import com.example.yanadu.data.repository.UserRepository;
import com.example.yanadu.data.request.OnGetData;
import com.example.yanadu.ui.NaviMainActivity;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements OnGetData {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.yanadu.R.layout.activity_login);

        Button btn_login = (Button) findViewById(R.id.btn_login);

        EditText et_id=(EditText) findViewById(R.id.et_id);
        EditText et_pwd=(EditText) findViewById(R.id.et_pwd);

        Button btn_gotojoin = (Button) findViewById(R.id.btn_gotojoin);

        final String[] TAG = {"Register"};
        final Boolean[] isExistBlank = {false};
        Boolean isPWSame = false;

        UserRepository u=new UserRepository(this);  //this는 OnGetData
        btn_gotojoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : click event
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });


        //로그인 클릭시 메인 화면으로 전환
        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if(et_id.toString().isEmpty() || et_pwd.toString().isEmpty() ){
                    isExistBlank[0] = true;
                    Toast.makeText(getApplicationContext(),"입력란을 모두 채워주세요.", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    u.requestSignIn(new UserData(et_id.getText().toString(),et_pwd.getText().toString()));
                    Log.d("loginsuccess",et_id.getText().toString()+et_pwd.getText().toString());

                }


            }
        });

    }

    @Override
    public void onGetData(ObjectData objectData) {
        if(!(((UserData)objectData).getResult())){
            Toast.makeText(getApplicationContext(),"ID와 PW를 다시 확인해주세요", Toast.LENGTH_SHORT).show();
        }

//        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        startActivity(intent);
        else
        {
            Toast.makeText(getApplicationContext(),"login success", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), NaviMainActivity.class);
            intent.putExtra("User",(UserData)objectData);
            startActivity(intent);
        }
    }

    @Override
    public void onGetDataList(List<ObjectData> objectDataList) {

    }

    @Override
    public void onSendDate(ObjectData objectData) {


    }
}