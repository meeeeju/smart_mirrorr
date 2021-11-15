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
import com.example.yanadu.data.model.SignInForm;
import com.example.yanadu.data.model.UserData;
import com.example.yanadu.data.repository.UserRepository;
import com.example.yanadu.data.request.OnGetData;
import com.example.yanadu.data.request.UserAPI;
import com.example.yanadu.ui.MainActivity;

import java.util.List;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity implements OnGetData {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.yanadu.R.layout.activity_login);

        Button btn_login = (Button) findViewById(R.id.btn_login);
      //  Button btn_signup = (Button) findViewById(R.id.btn_signup);
        EditText et_id=(EditText) findViewById(R.id.et_id);
        EditText et_pwd=(EditText) findViewById(R.id.et_pwd);

        final String[] TAG = {"Register"};
        final Boolean[] isExistBlank = {false};
        Boolean isPWSame = false;

        UserRepository u=new UserRepository(this);


//        btn_signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // TODO : click event
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//            }
//        });


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
                    Log.e("test",et_id.getText().toString()+et_pwd.getText().toString());

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    u.requestSignIn(new SignInForm(et_id.getText().toString(),et_pwd.getText().toString()));



                }


            }
        });


    }

    @Override
    public void onGetData(ObjectData objectData) {
        if(!(((UserData)objectData).getResult())){
            Toast.makeText(getApplicationContext(),"ID와 PW를 다시 확인해주세요", Toast.LENGTH_SHORT).show();
        }


//        else
//        {
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
//        }
    }

    @Override
    public void onGetDataList(List<ObjectData> objectDataList) {

    }
}