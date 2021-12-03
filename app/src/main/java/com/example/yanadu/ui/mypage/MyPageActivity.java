package com.example.yanadu.ui.mypage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.yanadu.R;
import com.example.yanadu.data.model.Note;
import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.model.UserData;
import com.example.yanadu.data.repository.UserRepository;
import com.example.yanadu.data.request.OnGetData;
import com.github.mikephil.charting.charts.BarChart;


import java.util.List;

public class MyPageActivity extends Fragment implements OnGetData {

    EditText user_name;
    EditText user_id;
    EditText user_email;
    EditText user_female;
    EditText user_male;


    @Override
    public void onGetData(ObjectData objectData) {


    }

    @Override
    public void onSendDate(ObjectData objectData) {

    }

    @Override
    public void onGetDataList(List<ObjectData> objectDataList) {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //setContentView(com.example.yanadu.R.layout.activity_my_page);
        View v = inflater.inflate(R.layout.activity_my_page, container, false);
        UserData u1=(UserData) getArguments().getSerializable("User");

        EditText user_name=(EditText) v.findViewById(R.id.my_name);
        EditText user_id=(EditText) v.findViewById(R.id.my_id);
        EditText user_email=(EditText) v.findViewById(R.id.my_Email);
        Button user_female=(Button) v.findViewById(R.id.my_btnfemale);
        Button user_male=(Button) v.findViewById(R.id.my_btnmale);
        String day = u1.getBirth();

        DatePicker dp = v.findViewById(R.id.my_birthSpinner);
        dp.updateDate(Integer.valueOf(day.substring(0, 4)), Integer.valueOf(day.substring(5, 7)) - 1, Integer.valueOf(day.substring(8, 10)) + 1);
        //u1.getBirth() String이라서



        user_name.setText(u1.getNickname());
        user_id.setText(u1.getId());
        user_email.setText(u1.getEmail());
        user_id.setText(u1.getId());
        String gender=u1.getSex();



        String birth;
        birth=u1.getBirth();


        if (gender.equals("W"))  //W:female
        {
            user_female.setBackgroundResource(R.color.real_maincolor);
        }
        else
        {
            user_male.setBackgroundResource(R.color.real_maincolor);
        }
        Button savebtn=(Button) v.findViewById(R.id.savebtn);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"저장을 완료했습니다", Toast.LENGTH_SHORT).show();

            }



        });

        return v;
    }

}