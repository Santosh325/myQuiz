package com.example.myquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button trueButton, falseButton;
    TextView question;
    ImageView nextButton,previousButton;
    ImageView resultOutput;
    private int currentQuestionIndex = 0;
    MediaPlayer media;
    Question[] questionsBank = new Question[] {
            new Question(R.string.questionOne,false),
            new Question(R.string.questionTwo,true),
            new Question(R.string.questionThree, false),
            new Question(R.string.questionFour,true),
            new Question(R.string.questionFive,true),
            new Question(R.string.questionSix,true),
            new Question(R.string.questionSeven,false)
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        question = findViewById(R.id.myQuestion);
        nextButton = findViewById(R.id.next_button);
        previousButton = findViewById(R.id.previous_button);
        resultOutput = findViewById(R.id.result);
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.true_button:
                checkAnswer(true);
                trueButton.setEnabled(false);
                trueButton.setAlpha(0.4f);
                falseButton.setEnabled(false);
                falseButton.setAlpha(0.4f);
                break;
            case R.id.false_button:
                checkAnswer(false);
                falseButton.setEnabled(false);
                falseButton.setAlpha(0.4f);
                trueButton.setEnabled(false);
                trueButton.setAlpha(0.4f);
                break;
            case R.id.next_button:
                currentQuestionIndex = (currentQuestionIndex + 1) % questionsBank.length;
               updateQuestion();
               trueButton.setAlpha(1f);
               trueButton.setEnabled(true);
                falseButton.setEnabled(true);
                falseButton.setAlpha(1f);
               resultOutput.setVisibility(View.GONE);
               break;
            case R.id.previous_button:
                if(currentQuestionIndex > 0) {
                    currentQuestionIndex = (currentQuestionIndex - 1) % questionsBank.length;
                    updateQuestion();
                    trueButton.setAlpha(1f);
                    trueButton.setEnabled(true);
                    falseButton.setEnabled(true);
                    falseButton.setAlpha(1f);
                    resultOutput.setVisibility(View.GONE);
                } else {
                    Toast.makeText(MainActivity.this,"Can't go back",Toast.LENGTH_SHORT).show();
                }



        }
    }

    public void updateQuestion() {

        Log.d("count","index Number " + currentQuestionIndex);
        question.setText(questionsBank[currentQuestionIndex].getMyQuestion());
    }


    private void checkAnswer(boolean userChooseAnswer) {
        boolean answerIsTrue = questionsBank[currentQuestionIndex].isAnswer();
        int toastMessage;

        if(userChooseAnswer == answerIsTrue) {
             toastMessage = R.string.correct_answer;
             resultOutput.setImageResource(R.drawable.ic_check_black_24dp);
             media = MediaPlayer.create(this,R.raw.correct);
             media.start();
             resultOutput.setVisibility(View.VISIBLE);
        } else {
            toastMessage = R.string.wrong_answer;
            resultOutput.setImageResource(R.drawable.ic_clear_black_24dp);
            resultOutput.setVisibility(View.VISIBLE);
            media = MediaPlayer.create(this,R.raw.wrong);
            media.start();
        }
        Toast.makeText(getApplicationContext(),toastMessage,Toast.LENGTH_LONG).show();
    }
}
