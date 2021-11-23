package com.example.yanadu.ui.extra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.yanadu.R;

import java.util.ArrayList;

public class RandomActivity extends Fragment {

    private ArrayList<maindata>arrayList;
    private mainadapter mainadapter;
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.activity_random, container, false);

        recyclerView=(RecyclerView) v.findViewById(R.id.rv);
        Object linearLayoutManager;
        linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager((RecyclerView.LayoutManager) linearLayoutManager);

        arrayList=new ArrayList<>();
        mainadapter=new mainadapter(arrayList);
        recyclerView.setAdapter(mainadapter);

        ImageButton imageButton6= (ImageButton) v.findViewById(R.id.imageButton6);
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                maindata maindata=new maindata("랜덤 미션을 지정해주세요.");
                arrayList.add(maindata);
                mainadapter.notifyDataSetChanged();
            }
        });
        return v;
    }


    }



