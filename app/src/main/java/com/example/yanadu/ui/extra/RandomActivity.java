package com.example.yanadu.ui.extra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.yanadu.R;

import java.util.ArrayList;

public class RandomActivity extends AppCompatActivity {

    private ArrayList<maindata>arrayList;
    private mainadapter mainadapter;
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.yanadu.R.layout.activity_random);


        recyclerView=(RecyclerView) findViewById(R.id.rv);
        Object linearLayoutManager;
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager((RecyclerView.LayoutManager) linearLayoutManager);

        arrayList=new ArrayList<>();
        mainadapter=new mainadapter(arrayList);
        recyclerView.setAdapter(mainadapter);

        ImageButton imageButton6= (ImageButton) findViewById(R.id.imageButton6);
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                maindata maindata=new maindata("랜덤 미션을 지정해주세요.");
                arrayList.add(maindata);
                mainadapter.notifyDataSetChanged();
            }
        });
    }
}