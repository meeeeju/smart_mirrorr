package com.example.yanadu.ui;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yanadu.R;
import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.model.ResultData;
import com.example.yanadu.data.model.UserData;
import com.example.yanadu.data.repository.ResultRepository;
import com.example.yanadu.data.request.OnGetData;
import com.example.yanadu.ui.graph_detail.WeekGraphActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;



public class RealMainActivity extends Fragment implements View.OnClickListener, OnGetData {

    public CardView maincard1;
    public ImageButton shufflebutton;
    public Button mircon;

    TextView bloodpressure,pulse,oxygen;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.activity_real_main, container, false);
        UserData u1=(UserData) getArguments().getSerializable("User");
        maincard1=(CardView) v.findViewById(R.id.maincard1);

        bloodpressure=v.findViewById(R.id.bloddpressure);
        pulse=v.findViewById(R.id.pulse);
        oxygen=v.findViewById(R.id.oxygen);

        ResultRepository resultService=new ResultRepository(this);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        resultService.requesthealthdailydata(u1.getId(),sdf.format(Calendar.getInstance().getTime()));

        maincard1.setOnClickListener(this);
        TextView username=v.findViewById(R.id.username);
        username.setText(u1.getNickname());

        shufflebutton= (ImageButton) v.findViewById(R.id.mainshuffle);
        shufflebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int randomnum=(int) (Math.random()*10);
                DigitTextView digitTextView = (DigitTextView) v.findViewById(R.id.digitTextView);
                digitTextView.setValue(randomnum);

            }
        });

       /* mircon=(Button) v.findViewById(R.id.mirrorconnect);
        mircon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),MirrorConnection.class);
                startActivity(intent);

            }
        });*/


        return v;



    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId())
        {
            case R.id.maincard1:
                i=new Intent(getActivity(), WeekGraphActivity.class);
                startActivity(i);
                break;

        }

    }


    @Override
    public void onGetData(ObjectData objectData) {
        ResultData rd = (ResultData) objectData;

        bloodpressure.setText(rd.getBloodMin()+"   "+rd.getBloodMax());
        pulse.setText(rd.getPulse()+"");
        oxygen.setText(rd.getO2()+"");

    }

    @Override
    public void onSendDate(ObjectData objectData) {

    }

    @Override
    public void onGetDataList(List<ObjectData> objectDataList) {

    }
}