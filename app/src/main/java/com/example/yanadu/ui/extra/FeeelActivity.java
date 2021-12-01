package com.example.yanadu.ui.extra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanadu.R;
import com.example.yanadu.ui.extra.Random.RandomActivity;
import com.example.yanadu.ui.extra.game.GameActivity;

public class FeeelActivity extends Fragment implements View.OnClickListener {


    public CardView card1, card2;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.activity_feeel, container, false);

        card1=(CardView) v.findViewById(R.id.card1);
        card2=(CardView) v.findViewById(R.id.card2);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId())
        {
            case R.id.card1:
                i=new Intent(getActivity(), GameActivity.class);
                startActivity(i);
                break;

            case R.id.card2:
                i=new Intent(getActivity(), RandomActivity.class);
                startActivity(i);
                break;

        }

    }
}