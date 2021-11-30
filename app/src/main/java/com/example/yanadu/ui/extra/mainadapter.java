package com.example.yanadu.ui.extra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanadu.R;

import java.util.ArrayList;

public class mainadapter extends RecyclerView.Adapter<mainadapter.CustomViewHolder> {

    private ArrayList<maindata>arrayList;

    public mainadapter(ArrayList<maindata> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public mainadapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        CustomViewHolder holder=new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull mainadapter.CustomViewHolder holder, int position) {
        //holder.et_mission.setText(arrayList.get(position).getEt_mission());


        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String curName=holder.et_mission.getText().toString();
                Toast.makeText(view.getContext(),curName,Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(holder.getAdapterPosition());
                return;
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null!=arrayList?arrayList.size():0);
    }

    public void remove(int position){
        try{
            arrayList.remove(position);
            notifyItemRemoved(position);

        }catch (IndexOutOfBoundsException ex)
        {
            ex.printStackTrace();
        }

    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected EditText et_mission;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.et_mission=(EditText) itemView.findViewById(R.id.et_mission);
        }
    }
}
