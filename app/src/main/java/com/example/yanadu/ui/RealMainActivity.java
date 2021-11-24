package com.example.yanadu.ui;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.yanadu.R;
import com.example.yanadu.ui.graph_detail.WeekGraphActivity;


public class RealMainActivity extends Fragment implements View.OnClickListener {

    public CardView card1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.activity_real_main, container, false);

        card1=(CardView) v.findViewById(R.id.card1);

        card1.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId())
        {
            case R.id.card1:
                i=new Intent(getActivity(), WeekGraphActivity.class);
                startActivity(i);
                break;

        }

    }
}