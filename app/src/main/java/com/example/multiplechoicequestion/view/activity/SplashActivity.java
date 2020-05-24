package com.example.multiplechoicequestion.view.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.fragment.app.FragmentManager;

import com.example.multiplechoicequestion.R;
import com.example.multiplechoicequestion.room.Category;
import com.example.multiplechoicequestion.room.Question;
import com.example.multiplechoicequestion.view.fragment.StartingFragment;
import com.example.multiplechoicequestion.view.viewmodel.QuestionViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SplashActivity extends Activity {

    Handler handler;
    FragmentManager fragmentManager;
    StartingFragment startingFragment;


    QuestionViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashfile);


        handler=new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, CategoryActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);


        mViewModel = new QuestionViewModel(getApplication());
        List<String> file = new ArrayList<>(Arrays.asList("Science-50", "Others-75", "Sports-50", "IQ-50", "History-100", "Entertenment-50", "Geography-50", "Full-forms-20", "Dharma-Sanskriti-75", "Litrature-50", "Rapid"));
        String filename = "_Export_DataFrame.jsondata.json";
        List<String> category = new ArrayList<>(Arrays.asList("Science", "Others", "Sports", "IQ", "History", "Entertainment", "Geography", "Full Forms", "Dharma-Sanskriti", "Literature", "Rapid Fire"));
        List<Integer> categorySetCount = new ArrayList<>();

        mViewModel.deleteAll();

        //  System.out.println(s);
        for (String f : file) {
            String s = loadJSONFromAsset(f.concat(filename));
            try {
                JSONObject obj;
                obj = new JSONObject(s);
                JSONObject questionObject = obj.getJSONObject("question");
                JSONObject option1Object = obj.getJSONObject("option1");
                JSONObject option2Object = obj.getJSONObject("option2");
                JSONObject option3Object = obj.getJSONObject("option3");
                JSONObject option4Object = obj.getJSONObject("option4");
                JSONObject answerObject = obj.getJSONObject("answer");
                JSONObject categoryObject = obj.getJSONObject("category");
                int rowLength = questionObject.length();
                int maxQuestion = 20;
                int setNr = 1;
                int questionCount = 0;
                for (int i = 0; i < questionObject.length(); i++) {
                    String index = Integer.toString(i);

                    questionCount++;
                    if (questionCount > maxQuestion) {
                        questionCount = 1;
                        setNr++;
                    }

                    Question q = new Question(questionObject.getString(index),
                            option1Object.getString(index), option2Object.getString(index),
                            option3Object.getString(index), option4Object.getString(index),
                            Integer.parseInt(answerObject.get(index).toString()), Integer.parseInt(categoryObject.getString(index)), setNr
                    );

                    //System.out.println(q.getCategoryId());
                    mViewModel.insert(q);
                }
                categorySetCount.add(setNr);
            } catch (JSONException e) {
                System.out.println("ODLSKDS");
                e.printStackTrace();
            }

        }

        int count = 0;
        for (String c : category) {
            Category cat = new Category(count, c, categorySetCount.get(count));
            mViewModel.insert(cat);
            count++;
        }
    }

    public String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().open(filename);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                json = new String(buffer, StandardCharsets.UTF_8);
            } else {
                json = new String(buffer);
            }


        } catch (IOException ex) {
            System.out.println("LoadJson error:");
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
