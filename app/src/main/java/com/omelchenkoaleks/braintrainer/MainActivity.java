package com.omelchenkoaleks.braintrainer;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView mOpinion_0_TextView;
    private TextView mOpinion_1_TextView;
    private TextView mOpinion_2_TextView;
    private TextView mOpinion_3_TextView;
    private TextView mTimerTextView;
    private TextView mScoreTextView;
    private TextView mQuestionTextView;

    private String mQuestionString;
    private int mRightAnswerInt;
    private int mRightAnswerPositionInt;
    private boolean mIsPositiveBoolean;
    private int mMinInt = 5;
    private int mMaxInt = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOpinion_0_TextView = findViewById(R.id.opinion_0_text_view);
        mOpinion_1_TextView = findViewById(R.id.opinion_1_text_view);
        mOpinion_2_TextView = findViewById(R.id.opinion_2_text_view);
        mOpinion_3_TextView = findViewById(R.id.opinion_3_text_view);
        mTimerTextView = findViewById(R.id.timer_text_view);
        mScoreTextView = findViewById(R.id.score_text_view);
        mQuestionTextView = findViewById(R.id.question_text_view);
    }

    // генерирует вопросы
    private void generateQuestion() {
        // получаем случайные числа от 5 до 30
        int a = (int) (Math.random() * (mMaxInt - mMinInt + 1) + mMinInt);
        int b = (int) (Math.random() * (mMaxInt - mMinInt + 1) + mMinInt);

        // получаем число либо 0 - либо 1
        int mark = (int) (Math.random() * 2);
        /*
            переменной присвоится значение true - если сгенерируется число 1
            и, значение false - если сгенерируется число 0
        */
        mIsPositiveBoolean = mark == 1;

        // теперь формируем правильный ответ
        if (mIsPositiveBoolean) {
            mRightAnswerInt = a + b;
            mQuestionString = String.format("%s + %s", a, b);
        } else {
            mRightAnswerInt = a - b;
            mQuestionString = String.format("%s + %s", a, b);
        }

        // генерируем индекс правильного ответа
        mRightAnswerPositionInt = (int) (Math.random() * 4);
    }
}
