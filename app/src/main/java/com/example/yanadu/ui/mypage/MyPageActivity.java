package com.example.yanadu.ui.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.yanadu.R;
import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.model.UserData;
import com.example.yanadu.data.repository.UserRepository;
import com.example.yanadu.data.request.OnGetData;

import java.util.List;

public class MyPageActivity extends AppCompatActivity implements OnGetData {

    EditText user_name;
    EditText user_id;
    EditText user_email;
    EditText user_female;
    EditText user_male;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.yanadu.R.layout.activity_my_page);
        UserData u1=(UserData) getIntent().getExtras().getSerializable("User");

        EditText user_name=(EditText) findViewById(R.id.my_name);
        EditText user_id=(EditText) findViewById(R.id.my_id);
        EditText user_email=(EditText) findViewById(R.id.my_Email);
        Button user_female=(Button) findViewById(R.id.my_btnfemale);
        Button user_male=(Button) findViewById(R.id.my_btnmale);
        Switch user_smoke=(Switch) findViewById(R.id.my_smoking);


        user_name.setText(u1.getNickname());
        user_id.setText(u1.getId());
        user_email.setText(u1.getEmail());
        user_id.setText(u1.getId());
        String gender=u1.getSex();



        String birth;
        birth=u1.getBirth();


        if (gender.equals("W"))  //W:female
        {
            user_female.setBackgroundResource(R.color.main_pink);
        }
        else
        {
            user_male.setBackgroundResource(R.color.main_pink);
        }



    }

    @Override
    public void onGetData(ObjectData objectData) {


    }

    @Override
    public void onSendDate(ObjectData objectData) {

    }

    @Override
    public void onGetDataList(List<ObjectData> objectDataList) {

    }
}