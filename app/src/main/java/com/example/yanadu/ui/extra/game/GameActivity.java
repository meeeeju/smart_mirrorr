package com.example.yanadu.ui.extra.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.yanadu.R;
import com.example.yanadu.ui.extra.Random.RandomDatabase;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    RecyclerView recyclerView;
    NoteAdapter adapter;

    Fragment mainFragment;
    EditText inputToDo;

    public static com.example.yanadu.ui.extra.game.NoteDatabase noteDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mainFragment = new GameFragment();

        initUI();

        Button saveButton = findViewById(R.id.saveBtnG);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                saveToDo();
                loadNoteListData();
            }
        });
        openDatabase();
        loadNoteListData();
    }

    private void initUI(){

        //recyclerView연결
        recyclerView = findViewById(R.id.recyclerViewGame);

        //LinearLayoutManager을 이용하여 recyclerView설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //어댑터 연결결
        adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

    }

    public int loadNoteListData(){

        //데이터를 가져오는 sql문 select... (id의 역순으로 정렬)
        String loadSql = "select _id, TODO from " + NoteDatabase.TABLE_NOTE + " order by _id desc";

        int recordCount = -1;
        NoteDatabase database = NoteDatabase.getInstance(this);

        if(database != null){
            //cursor를 객체화하여 rawQuery문 저장
            Cursor outCursor = database.rawQuery(loadSql);

            recordCount = outCursor.getCount();

            //_id, TODO가 담겨질 배열 생성
            ArrayList<LieNote> items = new ArrayList<>();

            //for문을 통해 하나하나 추가
            for(int i = 0; i < recordCount; i++){
                outCursor.moveToNext();

                int _id = outCursor.getInt(0);
                String todo = outCursor.getString(1);
                items.add(new LieNote(_id,todo));
            }
            outCursor.close();

            //어댑터에 연결 및 데이터셋 변경
            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }

        return recordCount;
    }

    private void saveToDo(){
        inputToDo = findViewById(R.id.inputToDo);

        //EditText에 적힌 글을 가져오기
        String todo = inputToDo.getText().toString();
        if (!(todo.equals("")))
        {
            //테이블에 값을 추가하는 sql구문 insert...
            String sqlSave = "insert into " + com.example.yanadu.ui.extra.game.NoteDatabase.TABLE_NOTE + " (TODO) values (" +
                    "'" + todo + "')";

            //sql문 실행
            com.example.yanadu.ui.extra.game.NoteDatabase database = com.example.yanadu.ui.extra.game.NoteDatabase.getInstance(this);
            database.execSQL(sqlSave);
            Toast.makeText(getApplicationContext(),"추가되었습니다.",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"입력칸을 채워주세요.",Toast.LENGTH_SHORT).show();
        }

//        //테이블에 값을 추가하는 sql구문 insert...
//        String sqlSave = "insert into " + NoteDatabase.TABLE_NOTE + " (TODO) values (" +
//                "'" + todo + "')";
//
//        //sql문 실행
//        NoteDatabase database = NoteDatabase.getInstance(context);
//        database.execSQL(sqlSave);

        //저장과 동시에 EditText 안의 글 초기화
        inputToDo.setText("");
    }

    public void openDatabase() {
        // open database
        if (noteDatabase != null) {
            noteDatabase.close();
            noteDatabase = null;
        }

        noteDatabase = com.example.yanadu.ui.extra.game.NoteDatabase.getInstance(this);
        boolean isOpen = noteDatabase.open();
        if (isOpen) {
            Log.d(TAG, "Note database is open.");
        } else {
            Log.d(TAG, "Note database is not open.");
        }


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (noteDatabase != null) {
            noteDatabase.close();
            noteDatabase = null;
        }
    }


}