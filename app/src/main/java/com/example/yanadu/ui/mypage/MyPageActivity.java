package com.example.yanadu.ui.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.yanadu.R;
import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.model.SignUpForm;
import com.example.yanadu.data.model.User;
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

        EditText user_name=(EditText) findViewById(R.id.my_name);
        EditText user_id=(EditText) findViewById(R.id.my_id);
        EditText user_email=(EditText) findViewById(R.id.my_Email);
        Button user_female=(Button) findViewById(R.id.my_btnfemale);
        Button user_male=(Button) findViewById(R.id.my_btnmale);
        Switch user_smoke=(Switch) findViewById(R.id.my_smoking);

        UserRepository u=new UserRepository(this);  //this는 OnGetData

        User u1=new User("권은구","dmsrn135");
        u.requestUserData(u1);

    }

    @Override
    public void onGetData(ObjectData objectData) {


        SignUpForm ud = (SignUpForm)objectData;

        user_name.setText(ud.getNickname());
        user_id.setText(ud.getId());
        user_email.setText(ud.getEmail());

        String gender;
        String birth;
        birth=ud.getBirth();
        gender=ud.getSex();

        Log.d("sex",gender);
        Log.d("birth",birth);
        Log.d("smoking",ud.getSmoking());

    }

    @Override
    public void onSendDate(ObjectData objectData) {

    }

    @Override
    public void onGetDataList(List<ObjectData> objectDataList) {

    }
}