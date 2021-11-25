package com.example.yanadu.ui.extra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.yanadu.R;

import java.util.ArrayList;

public class LieActivity extends AppCompatActivity {

    private ArrayList<LieData>arrayList;
    private Lieadaptor lieadaptor;
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lie);


        recyclerView=(RecyclerView) findViewById(R.id.rv);
        Object linearLayoutManager;
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager((RecyclerView.LayoutManager) linearLayoutManager);

        arrayList=new ArrayList<>();
        lieadaptor=new Lieadaptor(arrayList);
        recyclerView.setAdapter(lieadaptor);

        ImageButton random_add_list= (ImageButton) findViewById(R.id.random_add_list);
        random_add_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LieData liedata=new LieData("질문 리스트를 작성해 주세요!");
                arrayList.add(liedata);
                lieadaptor.notifyDataSetChanged();
            }
        });
    }

}