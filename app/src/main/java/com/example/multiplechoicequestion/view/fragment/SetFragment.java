package com.example.multiplechoicequestion.view.fragment;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multiplechoicequestion.R;
import com.example.multiplechoicequestion.room.Category;
import com.example.multiplechoicequestion.view.activity.CategoryActivity;
import com.example.multiplechoicequestion.view.adapter.SetListAdapter;
import com.example.multiplechoicequestion.view.viewmodel.QuestionViewModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.List;

public class SetFragment extends Fragment {

    private QuestionViewModel mViewModel;
    private SetListAdapter adapater;
    private int mCategoryIndex = 0;
    final String CAREGORY_INDEX = "categoryIndex";
    private TextView textView;
    private AdView mAdView;


    public static SetFragment newInstance() {
        return new SetFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.set_fragment, container, false);
        textView = view.findViewById(R.id.title);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        adapater = new SetListAdapter(getContext());
        recyclerView.setAdapter(adapater);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        Bundle b = getArguments();//taking arguments from startingFragment

        if (b != null) {
            int value = b.getInt("radio");
            System.out.println("VALUE: " + value);
            Log.i("value123", String.valueOf(value));
            adapater.setChecked(value);//transfering value
            mCategoryIndex = 10;
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView.setText("SET");
        if (savedInstanceState == null) {
            mCategoryIndex = ((CategoryActivity) getActivity()).categoryID;
        } else {
            mCategoryIndex = savedInstanceState.getInt(CAREGORY_INDEX);
        }
        mViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        System.out.println("Help: " + mCategoryIndex);
        mViewModel.getAllCategories().observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                System.out.println("Category INdex" + mCategoryIndex);
                System.out.println(" " + categories.size());

                adapater.setCategory(categories.get(mCategoryIndex));
            }
        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putInt(CAREGORY_INDEX, mCategoryIndex);

    }
}