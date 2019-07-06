package com.omelchenkoaleks.braintrainer;

import android.os.Bundle;
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
}
