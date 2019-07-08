package com.omelchenkoaleks.braintrainer;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

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

    private int mCountOfQuestions = 0;
    private int mCountOfRightAnswer = 0;

    // хранит значение - закончилась ли игра
    private boolean mGameOver = false;

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

        // создаем таймер
        CountDownTimer timer = new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                /*
                    вызываем созданный метод, для преобразования в строку милисекунды
                    и передаем в качестве параметра оставшееся количество милисекунд (millisUntilFinished)
                 */
                mTimerTextView.setText(getTime(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                mTimerTextView.setText(String.format(Locale.getDefault(), "%02d:%02d", 00, 00));
                mGameOver = true;

                // запускаем активити для вывода результата и начала новой игры
                Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                intent.putExtra("result", mCountOfRightAnswer);
                startActivity(intent);
            }
        };
        timer.start();
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

        String score = String.format("%s / %s", mCountOfRightAnswer, mCountOfQuestions);
        mScoreTextView.setText(score);
    }

    /* метод преобразует милисекунды (количество, которое остальсь
             до конца игры) в читаемый формат (String) */
    private String getTime(long millis) {
        // получаем секунды и минуты
        int seconds = (int) (millis / 1000); // общее количество секунд
        int minutes = seconds / 60;
        seconds = seconds % 60;

        // теперь возвращаем строку в нужном нам формате
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
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
        if (!mGameOver) {
            // чтобы проверить правильный ответ - нужно получить getText() у TextView
            TextView textView = (TextView) view;
            String answer = textView.getText().toString();
            int сhosenAnswer = Integer.parseInt(answer);
            if (сhosenAnswer == mRightAnswer) {
                // если правильный увеличиваем количество правильных ответов
                mCountOfRightAnswer++;
                Toast.makeText(this, "Верно", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Неверно", Toast.LENGTH_SHORT).show();
            }

            // независимо правильный или нет ответ увеличиваем количество общих вопросов
            mCountOfQuestions++;

            playNext();
        }
    }
}
