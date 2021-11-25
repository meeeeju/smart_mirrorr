package com.example.yanadu.ui.graph_detail.monthfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanadu.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MBloodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MBloodFragment extends Fragment {



    public MBloodFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = (ViewGroup) inflater.inflate(R.layout.fragment_m_blood, container, false);
        // Inflate the layout for this fragment
        return rootView;
    }
}