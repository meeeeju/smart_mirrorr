package com.example.yanadu.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.yanadu.R;
import com.example.yanadu.data.model.Note;
import com.example.yanadu.data.repository.ToDoRepository;

import java.util.ArrayList;

/*
직접 커스텀한 다이얼로그들을 띄워주고 다이얼로그 안에서의 동작을 정의하는 클래스 (싱글톤)
 */
public class CustomDialog extends Dialog {

    private static CustomDialog customDialog;
    private ToDoRepository todoService;
    private CustomDialog(@NonNull Context context) {
        super(context);
    }

    public static CustomDialog getInstance(Context context) {
        customDialog = new CustomDialog(context);

        return customDialog;
    }


    //다이얼로그 생성하기
    public void showDefaultDialog(ToDoRepository todoService, int i, ArrayList<Note> items ) {
        //참조할 다이얼로그 화면을 연결한다.
        customDialog.setContentView(R.layout.dialog1);

        //다이얼로그의 구성요소들이 동작할 코드작성

        Button button_ok = customDialog.findViewById(R.id.yesBtn);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoService.deleteToDO(items.get(i));

                items.remove(i);
                customDialog.dismiss();
//

            }
        });
        Button button_cancel = customDialog.findViewById(R.id.noBtn);
        button_cancel.setOnClickListener(clickCancel);
        customDialog.show();
    }

    //취소버튼을 눌렀을때 일반적인 클릭리스너
    View.OnClickListener clickCancel = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Toast.makeText(getContext(), "취소.", Toast.LENGTH_SHORT).show();
            customDialog.dismiss();
        }
    };

}
