package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView pointTextView;
    TextView timerTextView;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int l;
    int score=0;
    int numberOfquest=0;
    TextView sumtextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;

    public void playAgain(final View view){

        score=0;
        numberOfquest=0;

        timerTextView.setText("30s");
        pointTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestions();

        new CountDownTimer(30100,1000){


            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf(l/1000)+"s");

            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);

                timerTextView.setText("0s");

                resultTextView.setText("Done!"+Integer.toString(score)+ "/" + Integer.toString(numberOfquest));

            }
        }.start();

    }

    public void generateQuestions(){
        Random rand=new Random();

        int a=rand.nextInt(21);
        int b=rand.nextInt(21);

        sumtextView.setText(Integer.toString(a)+ " + "+Integer.toString(b));
        l=rand.nextInt(4);
        int incorrect;
        answers.clear();

        for (int i=0;i<4;i++){
            if(i==l){
                answers.add(a+b);
            }else{
                incorrect=rand.nextInt(41);
                while (incorrect==a+b){
                    incorrect=rand.nextInt(41);
                }
                answers.add(incorrect);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(l))){
            score++;
            resultTextView.setText("Correct!");

        }else {
            resultTextView.setText("Wrong!");
        }
        numberOfquest++;
        pointTextView.setText(Integer.toString(score)+ "/" + Integer.toString(numberOfquest));
        generateQuestions();
    }

    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton=(Button)findViewById(R.id.firstbutton);
        sumtextView=(TextView)findViewById(R.id.sumTextView3);
        button0=(Button)findViewById(R.id.button2);
        button1=(Button)findViewById(R.id.button3);
        button2=(Button)findViewById(R.id.button4);
        button3=(Button)findViewById(R.id.button5);
        resultTextView=(TextView)findViewById(R.id.resultsTextView4);
        pointTextView=(TextView)findViewById(R.id.pointsTextView2);
        timerTextView=(TextView)findViewById(R.id.timerTextView);
        playAgainButton=(Button)findViewById(R.id.playAgainButton);
        gameRelativeLayout=(RelativeLayout)findViewById(R.id.gameRelativeLayout);


       playAgain(findViewById(R.id.playAgainButton));

    }
}