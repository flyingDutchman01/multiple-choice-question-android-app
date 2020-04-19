package com.example.multiplechoicequestion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView tvQuestion;
    private TextView tvScore;
    private TextView tvQuestionCount;
    private TextView tvTimer;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;

    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    tvQuestion = findViewById(R.id.question_text);
    tvScore = findViewById(R.id.score);
    tvQuestionCount = findViewById(R.id.question_number);
    tvTimer = findViewById(R.id.tv_countdown);
    rbGroup = findViewById(R.id.radio_group);
    rb1 = findViewById(R.id.radio_button1);
    rb2 = findViewById(R.id.radio_button2);
    rb3 = findViewById(R.id.radio_button3);
    rb4 = findViewById(R.id.radio_button4);
    buttonConfirmNext = findViewById(R.id.submit_area);

    QuizDbHelper dbHelper = new QuizDbHelper(this);
    questionList = dbHelper.getAllQuestions();

    }
}
