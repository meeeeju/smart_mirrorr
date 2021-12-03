package com.example.yanadu.ui;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.yanadu.R;
import com.example.yanadu.ui.extra.Random.RandomDatabase;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;


public class DigitTextView extends FrameLayout {

    private static int ANIMATION_DURATION = 100;
    TextView currentTextView, nextTextView;
    ArrayList<StringData> string=new ArrayList<>();
    public int cur = 0;
    private int count = 0;
    public DigitTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        RandomDatabase db = RandomDatabase.getInstance(context);
        db.open();
        Cursor c = db.rawQuery("SELECT * FROM " + RandomDatabase.TABLE_NOTE);
        while(c.moveToNext()) {
            string.add(new StringData(count++, c.getString(1)));
        }
        db.close();
    }

    public int getItemCount(){
        return count;
    }

    public DigitTextView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        string.add(new StringData(0, "룰렛을 돌려주세요!"));

        LayoutInflater.from(context).inflate(R.layout.digit_text_view, this);
        currentTextView = (TextView) getRootView().findViewById(R.id.currentTextView);
        nextTextView = (TextView) getRootView().findViewById(R.id.nextTextView);

        nextTextView.setTranslationY(getHeight());

        setValue(0, 100);
    }

    public void setValue(final int desiredValue, int roll) {
        if (currentTextView.getText() == null || currentTextView.getText().length() == 0) {
            currentTextView.setText(string.get(desiredValue).val);
        }
        if(roll > 3 && desiredValue == cur) return;
        final int oldValue = cur;
        final int prev = oldValue + 1 != string.size() ? oldValue + 1 : 0;
        final int newroll = prev == 0 ? roll+1 : roll;
        nextTextView.setText(string.get(prev).val);

        currentTextView.animate().translationY(getHeight()).setDuration(ANIMATION_DURATION).start();
        nextTextView.setTranslationY(-nextTextView.getHeight());
        nextTextView.animate().translationY(0).setDuration(ANIMATION_DURATION).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}
            @Override
            public void onAnimationEnd(Animator animation) {
                currentTextView.setText(string.get(prev).val);
                currentTextView.setTranslationY(0);
                cur--;
                if(cur == -1) {
                    cur = string.size() - 1;
                }
                setValue(desiredValue, newroll);
            }
            @Override
            public void onAnimationCancel(Animator animation) {}
            @Override
            public void onAnimationRepeat(Animator animation) {}
        }).start();
    }
}