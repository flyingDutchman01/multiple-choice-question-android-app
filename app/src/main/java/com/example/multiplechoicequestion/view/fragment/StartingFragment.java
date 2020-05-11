package com.example.multiplechoicequestion.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.multiplechoicequestion.R;
import com.example.multiplechoicequestion.view.activity.QuizActivity;


public class StartingFragment extends Fragment {

    Button button;
    FragmentManager fragmentManager;
    CategoryFragment categoryFragment;
    RadioGroup radioGroup;
    RadioButton rbSelected;
    RadioButton radioButton1;
    RadioButton radioButton2;
    int radioChecked;
   //private  boolean answered=true;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_starting, container, false);

        button = v.findViewById(R.id.first_button);
        radioGroup = v.findViewById(R.id.radio);
        radioButton1 = v.findViewById(R.id.radio_button1);
        radioButton2 = v.findViewById(R.id.radio_button2);

        fragmentManager = getParentFragmentManager();
        categoryFragment = new CategoryFragment();

        //radio button fnunctions
//        rbSelected = v.findViewById(radioGroup.getCheckedRadioButtonId());
//        radioChecked = radioGroup.indexOfChild(rbSelected);
//        Log.i("value789",String.valueOf(radioChecked));





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(radioButton1.isChecked()){
                        Bundle args = new Bundle();// bundle stuffs
                        args.putInt("radio", 0);
                        categoryFragment.setArguments(args);
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, categoryFragment)
                                .addToBackStack("Stack")
                                .commit();
                    }else if(radioButton2.isChecked()){
                        Bundle args = new Bundle();// bundle stuffs
                        args.putInt("radio", 1);
                        categoryFragment.setArguments(args);
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, categoryFragment)
                                .addToBackStack("Stack")
                                .commit();

                    }else{
                        Toast.makeText(getContext(),"Please press a button",Toast.LENGTH_SHORT).show();
                    }
                radioGroup.clearCheck();

            }
        });

        return v;
    }



}
