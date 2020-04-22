package com.example.multiplechoicequestion.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multiplechoicequestion.R;
import com.example.multiplechoicequestion.view.fragment.CategoryFragment;

public class CategoryActivity extends AppCompatActivity {

    public int categoryID = 0;
    public int setNr = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CategoryFragment.newInstance())
                    .commitNow();
        }
    }
}
