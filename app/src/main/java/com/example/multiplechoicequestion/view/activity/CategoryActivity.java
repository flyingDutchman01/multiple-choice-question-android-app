package com.example.multiplechoicequestion.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multiplechoicequestion.R;
import com.example.multiplechoicequestion.view.fragment.CategoryFragment;
import com.example.multiplechoicequestion.view.fragment.StartingFragment;

public class CategoryActivity extends AppCompatActivity {

    public int categoryID = 0;
    public int setNr = 1;
    StartingFragment startingFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);

        startingFragment = new StartingFragment();
        //container motion

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, startingFragment)
                    .commitNow();
        }


    }

}
