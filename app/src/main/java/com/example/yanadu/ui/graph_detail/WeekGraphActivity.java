package com.example.yanadu.ui.graph_detail;

import android.util.Log;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yanadu.R;
import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.model.ResultData;
import com.example.yanadu.data.model.UserData;
import com.example.yanadu.data.repository.ResultRepository;
import com.example.yanadu.data.request.OnGetData;
import com.example.yanadu.ui.graph_detail.weekfragment.BloodFragment;
import com.example.yanadu.ui.graph_detail.weekfragment.O2Fragment;
import com.example.yanadu.ui.graph_detail.weekfragment.PulseFragment;
import com.example.yanadu.ui.graph_detail.weekfragment.PulseMonthFragment;
import com.example.yanadu.ui.graph_detail.weekfragment.O2MonthFragment;
import com.example.yanadu.ui.graph_detail.weekfragment.BloodMonthFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeekGraphActivity extends Fragment implements OnGetData {

    ArrayList<Double> pulseValueList = new ArrayList<Double>();
    ArrayList<Double> bloodMaxValueList = new ArrayList<Double>();
    ArrayList<Double> bloodMinValueList = new ArrayList<Double>();
    ArrayList<Double> o2ValueList = new ArrayList<Double>();


    BloodFragment bloodFrag = new BloodFragment();
    O2Fragment o2Frag = new O2Fragment();
    PulseFragment pulseFrag = new PulseFragment();

    PulseMonthFragment pulseMonthFrag;
    O2MonthFragment o2MonthFrag;
    BloodMonthFragment bloodMonthFrag;

    Button btn_fragmentA;
    Button btn_fragmentB ;
    Button btn_fragmentC ;
    //날짜 표시해주기
    TextView result_date;
    ArrayList<String> weekDate=new ArrayList<>();

    Button btn_week;
    Button btn_month;

    String selectedFrag="blood";

    int cur_frag;
    int is_week;

    UserData u1;
    Bundle bundle;

    Button dayprev, daynext;

    Calendar now;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.activity_week_graph, container, false);
        bundle=new Bundle();   //bundle 생성해서 보내주기

        ResultRepository resultService=new ResultRepository(this);
        u1=(UserData) getArguments().getSerializable("User");

        is_week = 1;
        cur_frag = 1;

        now = Calendar.getInstance();

        pulseMonthFrag = new PulseMonthFragment(getActivity().getApplicationContext());
        o2MonthFrag = new O2MonthFragment(getActivity().getApplicationContext());
        bloodMonthFrag = new BloodMonthFragment(getActivity().getApplicationContext());

        dayprev = (Button)v.findViewById(R.id.graphprev);
        daynext = (Button)v.findViewById(R.id.graphnext);

        dayprev.setText("<");
        daynext.setText(">");
        Log.d("useranddate",u1.getId()+":"+getCurrentDate());
        resultService.requestHealthWeeklydata(u1.getId(),getCurrentDate());  //
       // resultService.requestHealthdata(u1.getId());

        btn_fragmentA= v.findViewById(R.id.btn_fragmentblood);
        btn_fragmentB = v.findViewById(R.id.btn_fragmento2);
        btn_fragmentC = v.findViewById(R.id.btn_fragmentpulse);
        result_date=(TextView)v.findViewById(R.id.result_date);
        btn_week= v.findViewById(R.id.btn_weekly);
        btn_month = v.findViewById(R.id.btn_monthly);

        btn_month.setBackgroundResource(R.color.white);
        btn_week.setBackgroundResource(R.drawable.rectangle_background_pink);

        dayprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Fragment()).commit();
                if(is_week == 1){
                    now.add(Calendar.DAY_OF_MONTH, -7);
                    resultService.requestHealthWeeklydata(u1.getId(),getCurrentDate());
                }
                else{
                    now.add(Calendar.MONTH, -1);
                    resultService.requestHealthMonthlydata(u1.getId(),getCurrentDate());
                }
            }
        });

        daynext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Fragment()).commit();
                if(is_week == 1){
                    now.add(Calendar.DAY_OF_MONTH, 7);
                    resultService.requestHealthWeeklydata(u1.getId(),getCurrentDate());
                }
                else{
                    now.add(Calendar.MONTH, 1);
                    resultService.requestHealthMonthlydata(u1.getId(),getCurrentDate());
                }
            }
        });

        //버튼 클릭시 weekly/monthly 전환
        btn_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_week == 0)
                {
                    now = Calendar.getInstance();
                    btn_month.setBackgroundResource(R.color.white);
                    btn_week.setBackgroundResource(R.drawable.rectangle_background_pink);
                    is_week = 1;
                    resultService.requestHealthWeeklydata(u1.getId(),getCurrentDate());  //
                }
            }
        });
        btn_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_week == 1)
                {
                    now = Calendar.getInstance();
                    btn_week.setBackgroundResource(R.color.white);
                    btn_month.setBackgroundResource(R.drawable.rectangle_background_pink);
                    is_week = 0;
                    resultService.requestHealthMonthlydata(u1.getId(),getCurrentDate());
                }

            }
        });

        btn_fragmentA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cur_frag = 1;
                setToBlood();
            }
        });


        btn_fragmentB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cur_frag = 2;
                setToo2();
            }
        });

        btn_fragmentC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cur_frag = 3;
                setToPulse();
            }
        });

        return v;
    }




    @Override
    public void onGetData(ObjectData objectData) {

    }

    @Override
    public void onSendDate(ObjectData objectData) {

    }

    @Override
    public void onGetDataList(List<ObjectData> objectDataList) {
        pulseValueList.clear();
        bloodMaxValueList.clear();
        bloodMinValueList.clear();
        o2ValueList.clear();
        for(ObjectData od : objectDataList){
            ResultData rd = (ResultData)od;

            pulseValueList.add(rd.getPulse());
            bloodMaxValueList.add(rd.getBloodMax());
            bloodMinValueList.add(rd.getBloodMin());
            o2ValueList.add(rd.getO2());
        }

        weekDate.clear();
        //날짜 등록해주기
        ResultData first=(ResultData) objectDataList.get(0);
        ResultData second=(ResultData) objectDataList.get(objectDataList.size() - 1);
        weekDate.add(first.getDate());
        weekDate.add(second.getDate());

        btn_fragmentB.setBackgroundResource(R.color.white);
        btn_fragmentC.setBackgroundResource(R.color.white);


        //날짜 지정해주기
        if (objectDataList.size()>7){
           // result_date.setText(weekDate.get(0));
            //Date d = new Date(weekDate.get(0));
            result_date.setText(weekDate.get(0) + "~" + weekDate.get(1));
            o2MonthFrag.setValueList(o2ValueList);
            pulseMonthFrag.setValueList(pulseValueList);
            bloodMonthFrag.setValueList(bloodMinValueList, bloodMaxValueList);
        }
        else {
            o2Frag.setList(o2ValueList);
            bloodFrag.setList(bloodMinValueList,bloodMaxValueList);
            pulseFrag.setList(pulseValueList);
            result_date.setText(weekDate.get(0) + "~" + weekDate.get(1));
        }

        if(cur_frag == 1){
            setToBlood();
        }
        else if(cur_frag == 2){
            setToo2();
        }
        else {
            setToPulse();
        }
    }

    private String getCurrentDate() {
        Date date = now.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String formatedNow = formatter.format(date); // 포맷팅 적용
        return formatedNow;
    }

    private void setToBlood(){
        btn_fragmentB.setBackgroundResource(R.color.white);
        btn_fragmentC.setBackgroundResource(R.color.white);
        btn_fragmentA.setBackgroundResource(R.drawable.rectangle_background_pink);

        bundle.putSerializable("User", u1); //값 보내기
        bloodFrag.setArguments(bundle);
        if(is_week == 1)
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, bloodFrag).commit();
        else
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, bloodMonthFrag).commit();
    }

    private void setToPulse(){
        btn_fragmentA.setBackgroundResource(R.color.white);
        btn_fragmentB.setBackgroundResource(R.color.white);
        btn_fragmentC.setBackgroundResource(R.drawable.rectangle_background_pink);

        bundle.putSerializable("User", u1); //값 보내기
        pulseFrag.setArguments(bundle);
        if(is_week == 1)
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, pulseFrag).commit();
        else
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, pulseMonthFrag).commit();
    }

    private void setToo2(){
        btn_fragmentA.setBackgroundResource(R.color.white);
        btn_fragmentC.setBackgroundResource(R.color.white);
        btn_fragmentB.setBackgroundResource(R.drawable.rectangle_background_pink);

        bundle.putSerializable("User", u1); //값 보내기
        o2Frag.setArguments(bundle);
        if(is_week == 1)
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, o2Frag).commit();
        else
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, o2MonthFrag).commit();
    }
}