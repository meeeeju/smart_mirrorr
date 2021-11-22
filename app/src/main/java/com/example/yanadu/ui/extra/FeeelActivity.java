package com.example.yanadu.ui.extra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.yanadu.R;

public class FeeelActivity extends AppCompatActivity implements View.OnClickListener {


    public CardView card1, card2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.yanadu.R.layout.activity_feeel);

        card1=(CardView) findViewById(R.id.card1);
        card2=(CardView) findViewById(R.id.card2);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId())
        {
            case R.id.card1:
                i=new Intent(this,LieActivity.class);
                startActivity(i);
                break;

            case R.id.card2:
                i=new Intent(this,RandomActivity.class);
                startActivity(i);
                break;

        }

    }
}