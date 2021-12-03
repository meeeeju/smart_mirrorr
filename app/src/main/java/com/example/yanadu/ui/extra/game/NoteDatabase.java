package com.example.yanadu.ui.extra.game;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class NoteDatabase {
    private static final String TAG = "NoteDatabase";

    private static NoteDatabase database;
    public static String DATABASE_NAME = "todo.db";
    public static String TABLE_NOTE = "NOTE";
    public static int DATABASE_VERSION = 1;

    private Context context;
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;


    private NoteDatabase(Context context){
        this.context = context;
    }

    public static NoteDatabase getInstance(Context context){
        if(database == null){
            database = new NoteDatabase(context);
        }

        return database;
    }

    //이후 리스트를 표시할 때 현지위치를 나타내는 커서역할을 함
    public Cursor rawQuery(String SQL){

        Cursor c1 = null;
        try{
            c1 = db.rawQuery(SQL,null);
        } catch (Exception ex){
            Log.e(TAG,"Exception in rawQuery",ex);
        }

        return c1;
    }

    //sql문을 실행시키는 역할을 함
    public boolean execSQL(String SQL) {

        try {
            Log.d(TAG, "SQL : " + SQL);
            db.execSQL(SQL);
        } catch(Exception ex) {
            Log.e(TAG, "Exception in execSQL", ex);
            return false;
        }
        return true;
    }

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context, name,factory,version);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {


            //open();
            //테이블초기화
            String DROP_SQL = "drop table if exists " +TABLE_NOTE;

            try {
                db.execSQL(DROP_SQL);

            } catch (Exception ex){
                Log.e(TAG, "Exception in DROP_SQL", ex);
            }

            //테이블 생섣
            String CREATE_SQL = "create table " + TABLE_NOTE + "("
                    + " _id integer NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "  TODO TEXT DEFAULT '' "
                    + ")";
            try{
                db.execSQL(CREATE_SQL);
            } catch (Exception ex){
                Log.e(TAG,"Exception in CREATE_SQL", ex);
            }

            //테이블의 인덱스 붙이기
            String CREATE_INDEX_SQL = "create index " + TABLE_NOTE + "_IDX ON " + TABLE_NOTE + "("
                    + "_id"
                    + ")";
            try{
                db.execSQL(CREATE_INDEX_SQL);
            } catch (Exception ex){
                Log.e(TAG, "Exception in CREATE_INDEX_SQL",ex);
            }

            ArrayList<String> dummyLieData = new ArrayList<String>(){{
                add("난 너랑 같이 있는게 불편하다?!");
                add("힘들때 돈빌려달라고 하면 빌려줄 수 있다?!");
                add("엄마 아빠중에 누가 더 좋아?!");
                add("친구중에 나랑 제일 친하다?!");


            };}; // ArrayList 선언

            for (int i=0;i<dummyLieData.size();i++)
            {
                //테이블에 값을 추가하는 sql구문 insert...
                String sqlSave = "insert into " + NoteDatabase.TABLE_NOTE + " (TODO) values (" +
                        "'" + dummyLieData.get(i) + "')";
                //sql문 실행
                NoteDatabase database = NoteDatabase.getInstance(context);
                database.execSQL(sqlSave);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }

    //데이터베이스 열기
    public boolean open(){

        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = dbHelper.getWritableDatabase();

        return true;
    }

    //데이터베이스 닫기
    public void close(){
        db.close();
        database = null;
    }


}
