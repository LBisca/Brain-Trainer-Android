package com.example.lucas.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActivityChooserView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class MainActivity extends AppCompatActivity {

    Button start;
    Button button;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;
    TextView sumTextView;
    TextView resultTextView;
    TextView scoreView;
    TextView timer;
    RelativeLayout gameLayout;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int numberOfQuestions;
    int score;


    public void playAgain(final View view) {

        score = 0;
        numberOfQuestions = 0;

        timer.setText("30s");
        scoreView.setText("0/0");
        resultTextView.setText("");
        playAgain.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        generateQuestion();

        new CountDownTimer(3100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                timer.setText(String.valueOf(millisUntilFinished / 1000) + "s");
                score = 0;

            }

            @Override
            public void onFinish() {

                timer.setText("0s");
                resultTextView.setText("Done!");
                playAgain.setVisibility(View.VISIBLE);
                gameLayout.setVisibility(View.INVISIBLE);

            }
        }.start();
    }

    public void start(View view) {

        start.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);

        playAgain(findViewById(R.id.playAgain));

    }


    public void generateQuestion() {

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {

            if (i == locationOfCorrectAnswer) {

                answers.add(a + b);

            } else {

                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a + b) {

                    incorrectAnswer = rand.nextInt(41);

                }

                answers.add(incorrectAnswer);

            }

        }

        button.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void answer(View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            score++;
            resultTextView.setText("Correct!");

        } else {

            resultTextView.setText("Wrong!");

        }

        numberOfQuestions++;
        scoreView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.label);
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playAgain = (Button) findViewById(R.id.playAgain);
        resultTextView = (TextView) findViewById(R.id.bottomMsg);
        scoreView = (TextView) findViewById(R.id.score);
        timer = (TextView) findViewById(R.id.timer);
        gameLayout = (RelativeLayout) findViewById(R.id.gameLayout);



    }
}
