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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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


    Button btn_fragmentA;
    Button btn_fragmentB ;
    Button btn_fragmentC ;
    //날짜 표시해주기
    TextView result_date;
    ArrayList<String> weekDate=new ArrayList<>();

    Button btn_week;
    Button btn_month;

    String selectedFrag="blood";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.activity_week_graph, container, false);
        Bundle bundle=new Bundle();   //bundle 생성해서 보내주기

        ResultRepository resultService=new ResultRepository(this);
        UserData u1=(UserData) getArguments().getSerializable("User");

        Log.d("useranddate",u1.getId()+":"+getCurrentDate());
        resultService.requestHealthWeeklydata(u1.getId(),"2021/11/26");  //
       // resultService.requestHealthdata(u1.getId());




         btn_fragmentA= v.findViewById(R.id.btn_fragmentblood);
        btn_fragmentB = v.findViewById(R.id.btn_fragmento2);
        btn_fragmentC = v.findViewById(R.id.btn_fragmentpulse);
        result_date=(TextView)v.findViewById(R.id.result_date);
        btn_week= v.findViewById(R.id.btn_weekly);
        btn_month = v.findViewById(R.id.btn_monthly);




        //버튼 클릭시 weekly/monthly 전환
        btn_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bloodFrag.isWeek == 0)
                {
                    bloodFrag.isWeek = 1;
                    o2Frag.isWeek=1;
                    pulseFrag.isWeek=1;
                    resultService.requestHealthWeeklydata(u1.getId(),getCurrentDate());  //
                }
            }
        });
        btn_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bloodFrag.isWeek == 1)
                {
                    bloodFrag.isWeek = 0;
                    o2Frag.isWeek=0;
                    pulseFrag.isWeek=0;

                }

            }
        });



        btn_fragmentA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_fragmentB.setBackgroundResource(R.color.white);
                btn_fragmentC.setBackgroundResource(R.color.white);
                btn_fragmentA.setBackgroundResource(R.drawable.rectangle_background_pink);

                selectedFrag="blood";
                bundle.putSerializable("User", u1); //값 보내기
                bloodFrag.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, bloodFrag).commit();
            }
        });


        btn_fragmentB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_fragmentA.setBackgroundResource(R.color.white);
                btn_fragmentC.setBackgroundResource(R.color.white);
                btn_fragmentB.setBackgroundResource(R.drawable.rectangle_background_pink);

                selectedFrag="o2";

                bundle.putSerializable("User", u1); //값 보내기
                o2Frag.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, o2Frag).commit();

            }
        });

        btn_fragmentC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_fragmentA.setBackgroundResource(R.color.white);
                btn_fragmentB.setBackgroundResource(R.color.white);
                btn_fragmentC.setBackgroundResource(R.drawable.rectangle_background_pink);

                selectedFrag="pulse";

                bundle.putSerializable("User", u1); //값 보내기
                pulseFrag.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, pulseFrag).commit();
            }
        });

        //기본 설정
//        btn_fragmentB.setBackgroundResource(R.color.white);
//        btn_fragmentC.setBackgroundResource(R.color.white);
//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, bloodFrag).commit();


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
        for(ObjectData od : objectDataList){
            ResultData rd = (ResultData)od;

            pulseValueList.add(rd.getPulse());
            bloodMaxValueList.add(rd.getBloodMax());
            bloodMinValueList.add(rd.getBloodMin());
            o2ValueList.add(rd.getO2());
        }

        o2Frag.setList(o2ValueList);
        bloodFrag.setList(bloodMinValueList,bloodMaxValueList);
        pulseFrag.setList(pulseValueList);

        //날짜 등록해주기
        ResultData first=(ResultData) objectDataList.get(0);
        ResultData second=(ResultData) objectDataList.get(6);
        weekDate.add(first.getDate());
        weekDate.add(second.getDate());

//        o2Frag.setDate(first,second);
//        pulseFrag.setDate(first,second);

        //날짜 지정해주기
        if (objectDataList.size()>7){
           // result_date.setText(weekDate.get(0));
            Date d = new Date(weekDate.get(0));
            result_date.setText(d.getYear());

        }
        else
            result_date.setText(weekDate.get(0)+"~"+weekDate.get(1));


//        if (what_clicked.equals("week"))
//        {
//            bloodFrag.showBarChart();//weekly bar chart 그려줌
//            o2Frag.showBarChart();
//            pulseFrag.showBarChart();
//
//        }
//        else
//        {
//
//        }

        if (getActivity().getSupportFragmentManager().findFragmentById(R.id.frameLayout)==null)
        {
            btn_fragmentB.setBackgroundResource(R.color.white);
            btn_fragmentC.setBackgroundResource(R.color.white);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, bloodFrag).commit();
        }
        else{
            switch(selectedFrag){
                case "blood":
                    if (bloodFrag.isWeek==1)
                        bloodFrag.showBarChart();//weekly bar chart 그려줌
                    else
                        bloodFrag.showBarChart();

                    break;
                case "o2":
                    o2Frag.showBarChart();
                     break;
                case "pulse":
                    pulseFrag.showBarChart();
                    break;

            }


        }


    }

    private String getCurrentDate() {
        //System.out.println(now); 현재 시간 출력
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String formatedNow = formatter.format(now); // 포맷팅 적용
        return formatedNow;

    }


}