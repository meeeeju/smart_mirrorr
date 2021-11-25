package com.example.yanadu.ui.schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanadu.R;
import com.example.yanadu.data.model.Note;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class NoteAdapter  extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    private static final String Tag="NoteAdapter";

    static ArrayList<Note> items = new ArrayList<Note>();



    @NonNull
    @NotNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.todo_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NoteAdapter.ViewHolder holder, int position) {
        Note item = items.get(position);
        holder.setItem(item);
        holder.setLayout();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout layoutTodo;
        CheckBox checkBox;
        Button deleteButton;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            layoutTodo = itemView.findViewById(R.id.layoutTodo);
            checkBox = itemView.findViewById(R.id.checkBox);
            deleteButton = itemView.findViewById(R.id.btn_deleteButton);


            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Note n = items.get(getAdapterPosition());
//                    n.get_id()
                    String TODO = n.getTodo();

                    deleteToDo(TODO);
                    Toast.makeText(v.getContext(),"삭제되었습니다.",Toast.LENGTH_SHORT).show();
                }

                private void deleteToDo(String TODO){
                    //서버로 삭제 요청
                }
            });
        }
        //editText에서 입력받은 텍스트를 체크박스에 넣도록 해줌
        public void setItem(Note item){checkBox.setText(item.getTodo());}

        //아이템들을 담은 LinearLayout을 보여주게하는 메서드
        public void setLayout(){layoutTodo.setVisibility(View.VISIBLE);}

    }

    public void setItems(ArrayList<Note> items){this.items = items;}



}
