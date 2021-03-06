package com.example.multiplechoicequestion.view.activity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.multiplechoicequestion.R;
import com.example.multiplechoicequestion.room.CategoricalQuestion;
import com.example.multiplechoicequestion.room.Question;
import com.example.multiplechoicequestion.view.viewmodel.QuestionViewModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{

    private QuestionViewModel mViewModel;

    public static final String EXTRA_SCORE ="extraScore";
    public static final String CATEGORY_ID = "categoryId";
    public static final String SET_NR = "setNr";
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private TextView tvQuestion;
    private TextView tvScore;
    private TextView tvQuestionCount;
    private TextView tvTimer;
    private Button rb1;
    private Button rb2;
    private Button rb3;
    private Button rb4;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private HashMap options;
    private ArrayList optionsPos;

    private int score;


    private long backPressedTime;
    private int categoryIndex;
    private int setNr;

    private InterstitialAd interstitialAd;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        categoryIndex = getIntent().getIntExtra(CATEGORY_ID, 0);
        setNr = getIntent().getIntExtra(SET_NR, 1);

        tvQuestion = findViewById(R.id.question_text);
        tvScore = findViewById(R.id.score);
        tvQuestionCount = findViewById(R.id.question_number);
        tvTimer = findViewById(R.id.tv_countdown);
        rb1 = findViewById(R.id.btn1);
        rb2 = findViewById(R.id.btn2);
        rb3 = findViewById(R.id.btn3);
        rb4 = findViewById(R.id.btn4);

        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);
        rb4.setOnClickListener(this);
        rb1.setEnabled(false);
        rb2.setEnabled(false);
        rb3.setEnabled(false);
        rb4.setEnabled(false);
        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = tvTimer.getTextColors();

        mViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        //questionList = mViewModel.getAllWords().getValue();

        System.out.println();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mViewModel.getAllCategoricalQuestions().observe(this, new Observer<List<CategoricalQuestion>>() {
            @Override
            public void onChanged(List<CategoricalQuestion> questions) {
                //adapater.setWords(questions);
                questionList = questions.get(categoryIndex).questionList;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    questionList = questionList.stream()
                            .filter(q -> q.getSetNr() == setNr).collect(Collectors.toList());
                } else {
                    List<Question> newQuestionList = new ArrayList<>();
                    for (Question question : questionList) {
                        if (question.getSetNr() == setNr)
                            newQuestionList.add(question);
                    }
                    questionList = newQuestionList;
                }
                questionCountTotal = questionList.size();


                Collections.shuffle(questionList);

                showNextQuestion();
            }
        });

        //interstetialAd

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstetial_ad_unit_id));
        AdRequest adRequests = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequests);



    }

    private void showNextQuestion(){

        if(questionCounter < questionCountTotal){
            currentQuestion = questionList.get(questionCounter);
            options = new HashMap<>();
            options.put(1, currentQuestion.getOption1());
            options.put(2, currentQuestion.getOption2());
            options.put(3, currentQuestion.getOption3());
            options.put(4, currentQuestion.getOption4());
            List keys = new ArrayList(options.keySet());
            Collections.shuffle(keys);
            optionsPos = new ArrayList();
            for (Object o : keys) {
                // Access keys/values in a random order
                optionsPos.add(o);
            }
            System.out.println("NOw");
            System.out.println("Answer:" + currentQuestion.getAnswerNr());

            System.out.println(optionsPos);
            System.out.println(options);
            playAnim(tvQuestion,0,0);
            playAnim(rb1,0,1);
            playAnim(rb2,0,2);
            playAnim(rb3,0,3);
            playAnim(rb4,0,4);

            rb1.setEnabled(true);
            rb2.setEnabled(true);
            rb3.setEnabled(true);
            rb4.setEnabled(true);
            questionCounter++;
            tvQuestionCount.setText("Question: "+ questionCounter + "/"  + questionCountTotal);

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        }else{
            finishQuiz();
        }
    }
    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                showNextQuestion();
            }
        }.start();
    }
    private void updateCountDownText(){
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        tvTimer.setText(timeFormatted);

        if (timeLeftInMillis < 10000){
            tvTimer.setTextColor(Color.RED);
        }else{
            tvTimer.setTextColor(textColorDefaultCd);
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(int selectedOption, View view){
        rb1.setEnabled(false);
        rb2.setEnabled(false);
        rb3.setEnabled(false);
        rb4.setEnabled(false);
        if(selectedOption == currentQuestion.getAnswerNr())
        {
            //Right Answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            score++;
            tvScore.setText("Score: "+ score);
        }
        else
        {
            //Wrong Answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            switch (currentQuestion.getAnswerNr())
            {
                case 1:
                    rb1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 2:
                    rb2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 3:
                    rb3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 4:
                    rb4.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;

            }

        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showNextQuestion();
            }
        }, 2000);

    }



    private void finishQuiz(){
        interstitialAd.show();
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE,score);
        setResult(RESULT_OK, resultIntent);
        finish();

    }



    private void playAnim(final View view, final int value, final int viewNum)
    {

        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500)
                .setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if(value == 0)
                        {
                            switch (viewNum)
                            {
                                case 0:
                                    ((TextView)view).setText(currentQuestion.getQuestion());
                                    break;
                                case 1:
                                    ((Button)view).setText((String)options.get((int)optionsPos.get(0)));
                                    break;
                                case 2:
                                    ((Button)view).setText((String)options.get((int)optionsPos.get(1)));
                                    break;
                                case 3:
                                    ((Button)view).setText((String)options.get((int)optionsPos.get(2)));
                                    break;
                                case 4:
                                    ((Button)view).setText((String)options.get((int)optionsPos.get(3)));
                                    break;

                            }


                            if(viewNum != 0)
                                ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#f4fa9c")));


                            playAnim(view,1,viewNum);

                        }

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

    }

    @Override
    public void onBackPressed() {
        if(backPressedTime+2000 > System.currentTimeMillis()){
            finishQuiz();
        }else{
            Toast.makeText(QuizActivity.this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        int selectedOption = 0;

        switch (v.getId())
        {
            case R.id.btn1 :
                selectedOption = (int)optionsPos.get(0);
                break;

            case R.id.btn2:
                selectedOption = (int)optionsPos.get(1);
                break;

            case R.id.btn3:
                selectedOption = (int)optionsPos.get(2);
                break;

            case R.id.btn4:
                selectedOption = (int)optionsPos.get(3);
                break;

            default:
        }
        System.out.println("Answer: "+currentQuestion.getAnswerNr());
        System.out.println(options);
        System.out.println(optionsPos);
        System.out.println("Selected OPition: " + selectedOption);
        countDownTimer.cancel();
        checkAnswer(selectedOption, v);
    }
}
