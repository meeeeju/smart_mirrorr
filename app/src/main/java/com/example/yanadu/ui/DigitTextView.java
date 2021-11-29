package com.example.yanadu.ui;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.yanadu.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;


public class DigitTextView extends FrameLayout {

    private static int ANIMATION_DURATION = 250;
    TextView currentTextView, nextTextView;
    ArrayList<StringData> string=new ArrayList<>();
    public int cur = 0;
    public DigitTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public DigitTextView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {


        string.add(new StringData(0, "신천역에서 학교까지 택시 안타고 걸어가기"));
        string.add(new StringData(1, "하루 물 3잔 마시기!"));
        string.add(new StringData(2, "하루에 택시 한번타기"));
        string.add(new StringData(3, "dasdf"));
        string.add(new StringData(4, "안녕하세요 안녕하세요 안녕하세요 앙ㄴ녕하세요 안녕하세요 안녕"));
        string.add(new StringData(5, "f"));
        string.add(new StringData(6, "asdf"));
        string.add(new StringData(7, "wethg"));
        string.add(new StringData(8, "myty"));
        string.add(new StringData(9, "cx"));



        LayoutInflater.from(context).inflate(R.layout.digit_text_view, this);
        currentTextView = (TextView) getRootView().findViewById(R.id.currentTextView);
        nextTextView = (TextView) getRootView().findViewById(R.id.nextTextView);

        nextTextView.setTranslationY(getHeight());

        setValue(0);
    }

    public void setValue(final int desiredValue) {
        if (currentTextView.getText() == null || currentTextView.getText().length() == 0) {
            currentTextView.setText(string.get(desiredValue).val);
        }

        final int oldValue = cur;

        if (oldValue > desiredValue) {
            nextTextView.setText(string.get(oldValue-1).val);

            currentTextView.animate().translationY(-getHeight()).setDuration(ANIMATION_DURATION).start();
            nextTextView.setTranslationY(nextTextView.getHeight());
            nextTextView.animate().translationY(0).setDuration(ANIMATION_DURATION).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {}
                @Override
                public void onAnimationEnd(Animator animation) {
                    currentTextView.setText(string.get(oldValue-1).val);
                    currentTextView.setTranslationY(0);
                    if (oldValue - 1 != desiredValue) {
                        cur--;
                        setValue(desiredValue);
                    }
                }
                @Override
                public void onAnimationCancel(Animator animation) {}
                @Override
                public void onAnimationRepeat(Animator animation) {}
            }).start();
        } else if (oldValue < desiredValue) {
            nextTextView.setText(string.get(oldValue+1).val);

            currentTextView.animate().translationY(getHeight()).setDuration(ANIMATION_DURATION).start();
            nextTextView.setTranslationY(-nextTextView.getHeight());
            nextTextView.animate().translationY(0).setDuration(ANIMATION_DURATION).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {}
                @Override
                public void onAnimationEnd(Animator animation) {
                    currentTextView.setText(string.get(oldValue+1).val);
                    currentTextView.setTranslationY(0);
                    if (oldValue + 1 != desiredValue) {
                        cur++;
                        setValue(desiredValue);
                    }
                }
                @Override
                public void onAnimationCancel(Animator animation) {}
                @Override
                public void onAnimationRepeat(Animator animation) {}
            }).start();
        }
    }
}