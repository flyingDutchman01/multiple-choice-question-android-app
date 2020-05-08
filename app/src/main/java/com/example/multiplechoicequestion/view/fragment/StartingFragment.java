package com.example.multiplechoicequestion.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.multiplechoicequestion.R;


public class StartingFragment extends Fragment {

    Button button;
    FragmentManager fragmentManager;
    CategoryFragment categoryFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v =  inflater.inflate(R.layout.fragment_starting, container, false);
      button = v.findViewById(R.id.first_button);
        fragmentManager = getParentFragmentManager();
      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              fragmentManager.beginTransaction()
                      .replace(R.id.container, CategoryFragment.newInstance())
                      .addToBackStack("Stack")
                      .commit();
          }
      });

      return v;
    }
}
