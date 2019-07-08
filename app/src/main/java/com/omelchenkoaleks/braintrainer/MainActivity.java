package com.omelchenkoaleks.braintrainer;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView mOpinion_0_TextView;
    private TextView mOpinion_1_TextView;
    private TextView mOpinion_2_TextView;
    private TextView mOpinion_3_TextView;
    private TextView mTimerTextView;
    private TextView mScoreTextView;
    private TextView mQuestionTextView;

    /*
        текст в TextView будет устанавливаться в цикле,
        поэтому нужен массив
     */
    private ArrayList<TextView> mOptions = new ArrayList<>();

    private String mQuestion;
    private int mRightAnswer;
    private int mRightAnswerPosition;
    private boolean mIsPositive;
    private int mMin = 5;
    private int mMax = 30;

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

        mOptions.add(mOpinion_0_TextView);
        mOptions.add(mOpinion_1_TextView);
        mOptions.add(mOpinion_2_TextView);
        mOptions.add(mOpinion_3_TextView);

        playNext();
    }

    // новый вопрос должен генерироваться каждый раз при выборе варианта
    private void playNext() {
        generateQuestion();

        for (int i = 0; i < mOptions.size(); i++) {
            if (i == mRightAnswerPosition) {
                mOptions.get(i).setText(Integer.toString(mRightAnswer));
            } else {
                mOptions.get(i).setText(Integer.toString(generateWrongAnswer()));
            }
        }
    }

    // генерирует вопросы
    private void generateQuestion() {
        // получаем случайные числа от 5 до 30
        int a = (int) (Math.random() * (mMax - mMin + 1) + mMin);
        int b = (int) (Math.random() * (mMax - mMin + 1) + mMin);

        // получаем число либо 0 - либо 1
        int mark = (int) (Math.random() * 2);
        /*
            переменной присвоится значение true - если сгенерируется число 1
            и, значение false - если сгенерируется число 0
        */
        mIsPositive = mark == 1;

        // теперь формируем правильный ответ
        if (mIsPositive) {
            mRightAnswer = a + b;
            mQuestion = String.format("%s + %s", a, b);
        } else {
            mRightAnswer = a - b;
            mQuestion = String.format("%s - %s", a, b);
        }

        mQuestionTextView.setText(mQuestion);

        // генерируем индекс правильного ответа
        mRightAnswerPosition = (int) (Math.random() * 4);
    }

    /*
            генерирует неправильные ответы и возвращает результаты,
            используем при установке текста у TextView с вариантами ответа
      */
    private int generateWrongAnswer() {
        /*
            число от -25 до 35 (генерируем)
            выполняем до тех пор, пока полученное число не будет
            отличаться от правильного ответа - чтобы не произошло совпадение ...
          */
        int result;
        do {
            result = (int) (Math.random() * mMax * 2 + 1) - (mMax - mMin);
        } while (result == mRightAnswer);
        return result;
    }

    public void onClickAnswer(View view) {
        // чтобы проверить правильный ответ - нужно получить getText() у TextView
        TextView textView = (TextView) view;
        String answer = textView.getText().toString();
        int сhosenAnswer = Integer.parseInt(answer);
        if (сhosenAnswer == mRightAnswer) {
            Toast.makeText(this, "Верно", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Неверно", Toast.LENGTH_SHORT).show();
        }

        playNext();
    }
}
