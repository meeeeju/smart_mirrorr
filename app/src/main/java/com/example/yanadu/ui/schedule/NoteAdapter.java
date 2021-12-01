package com.example.yanadu.ui.schedule;

import android.util.Log;
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
import com.example.yanadu.data.model.CheckReturn;
import com.example.yanadu.data.model.Note;
import com.example.yanadu.data.model.ObjectData;
import com.example.yanadu.data.repository.ToDoRepository;
import com.example.yanadu.data.request.OnGetData;
import com.example.yanadu.utils.CustomDialog;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter  extends RecyclerView.Adapter<NoteAdapter.ViewHolder> implements OnGetData {

    private static final String Tag="NoteAdapter";

    static ArrayList<Note> items = new ArrayList<Note>();
    View itemView;
    static ToDoRepository ToDoservice;



    @NonNull
    @NotNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        itemView = inflater.inflate(R.layout.todo_items, parent, false);
        ToDoservice=new ToDoRepository(this);

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

    @Override
    public void onGetData(ObjectData objectData) {
        CheckReturn cr=(CheckReturn) objectData;
        Log.d("delete return value",cr.getCheck()+"");
        if(cr.getCheck()==true)
        {
            Toast.makeText(itemView.getContext(),"삭제되었습니다.",Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        }
        else{
            Toast.makeText(itemView.getContext(),"오류가 발생했습니다.",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSendDate(ObjectData objectData) {

    }

    @Override
    public void onGetDataList(List<ObjectData> objectDataList) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout layoutTodo;
        CheckBox checkBox;
        Button deleteButton;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            layoutTodo = itemView.findViewById(R.id.layoutRandom);
            checkBox = itemView.findViewById(R.id.checkBox);
            deleteButton = itemView.findViewById(R.id.btn_deleteButton);



            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Note n = items.get(getAdapterPosition());
                    items.remove(getAdapterPosition());
//
                    int todo_id = n.get_id();  //todo_id는 pk임
                    Log.d("todo_id",todo_id+"현재 삭제시킬 _id 값");

                    ToDoservice.deleteToDO(n);
                   // Toast.makeText(v.getContext(),"삭제되었습니다.",Toast.LENGTH_SHORT).show();
                }


            });


            checkBox.setOnClickListener(new CheckBox.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO : process the click event.
                    if (checkBox.isChecked()) {
                        // TODO : CheckBox is checked.
                        CustomDialog.getInstance(v.getContext()).showDefaultDialog(ToDoservice,getAdapterPosition(),items);
                    } else {
                        // TODO : CheckBox is unchecked.
                    }



                }
            }) ;
        }
        //editText에서 입력받은 텍스트를 체크박스에 넣도록 해줌
        public void setItem(Note item){checkBox.setText(item.getTodo());}

        //아이템들을 담은 LinearLayout을 보여주게하는 메서드
        public void setLayout(){layoutTodo.setVisibility(View.VISIBLE);}

    }

    public void setItems(ArrayList<Note> items){this.items = items;}



}
