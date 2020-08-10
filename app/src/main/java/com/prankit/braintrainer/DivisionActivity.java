package com.prankit.braintrainer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class DivisionActivity extends AppCompatActivity {
    Button startButton, button3, button1, button2, button4, playAgainButton, backButton;
    TextView resultTextView, sumtextView, timerTextView, pointsTextView;
    GridLayout gridLayout;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    ArrayList<Integer> factorList = new ArrayList<Integer>();
    int locationOfCorrectAnswer, factorListLength;
    int score = 0;
    int numberOfQuestion = 0;
    boolean counterIsActive = false;

    public void back(View view){
        new AlertDialog.Builder(this)
                .setMessage("Do you want to return to main menu")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }

    public void playAgain(View view) {
        counterIsActive = true;
        if (counterIsActive) {
            score = 0;
            numberOfQuestion = 0;
            timerTextView.setText("30s");
            pointsTextView.setText("0/0");
            resultTextView.setText("");
            playAgainButton.setVisibility(View.INVISIBLE);
            backButton.setVisibility(View.INVISIBLE);
            generateQuestion();
            new CountDownTimer(30100, 1000) {
                @Override
                public void onTick(long l) {
                    timerTextView.setText(String.valueOf(l / 1000) + "s");
                }
                @Override
                public void onFinish() {
                    playAgainButton.setVisibility(View.VISIBLE);
                    backButton.setVisibility(View.VISIBLE);
                    timerTextView.setText("0s");
                    resultTextView.setText("Your score : " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
                    counterIsActive = false;
                }
            }.start();
        }
    }

    public boolean isPrime(int num){
        int counter = 0;
        for (int i=1 ; i < num/2; i++){
            if(num % i == 0){
                counter++;
            }
            else
                continue;;
        }
        if (counter >= 2)
            return false;
        else
            return true;
    }

    public void factor(int num){
        factorList.clear();
        for (int i = 1; i < num/2; i++){
            if (num % i == 0){
                factorList.add(i);
            }
        }
        factorListLength = factorList.size();
    }

    public void generateQuestion() {
        if (counterIsActive) {
            Random rand = new Random();         // generate 2 random number always for addition
            int a = rand.nextInt(201) + 4;
            if (isPrime(a)){
                a = rand.nextInt(201) + 4;
                while (isPrime(a)){
                    a = rand.nextInt(201) + 4;
                }
            }
            factor(a);
            int factorListIndex = rand.nextInt(factorListLength);
            int b = factorList.get(factorListIndex);
            sumtextView.setText(Integer.toString(a) + "/" + Integer.toString(b));
            locationOfCorrectAnswer = rand.nextInt(4);
            answer.clear();
            int incorrectAnswer;
            for (int i = 0; i < 4; i++) {
                if (i == locationOfCorrectAnswer) {
                    answer.add(a / b);
                } else {
                    incorrectAnswer = rand.nextInt(11);
                    while (incorrectAnswer == a / b) {
                        incorrectAnswer = rand.nextInt(11);
                    }
                    answer.add(incorrectAnswer);
                }
            }
            button1.setText(Integer.toString(answer.get(0)));
            button2.setText(Integer.toString(answer.get(1)));
            button3.setText(Integer.toString(answer.get(2)));
            button4.setText(Integer.toString(answer.get(3)));
        }
    }

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        counterIsActive = true;
        timerTextView.setVisibility(View.VISIBLE);
        pointsTextView.setVisibility(View.VISIBLE);
        sumtextView.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    public void chooseAnswer(View view) {
        if (counterIsActive == true) {
            if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
                score++;
                resultTextView.setText("Correct !");
            } else {
                resultTextView.setText("Wrong !");
            }
            numberOfQuestion++;
            pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
            generateQuestion();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);

        Intent intent = getIntent();

        resultTextView = (TextView) findViewById(R.id.resultTextView);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        startButton = (Button) findViewById(R.id.startButton);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        pointsTextView = (TextView) findViewById(R.id.pointTextView);
        sumtextView = (TextView) findViewById(R.id.sumTextView);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        backButton = (Button) findViewById(R.id.backButton);
        // playAgain(findViewById(R.id.startButton));
    }
}