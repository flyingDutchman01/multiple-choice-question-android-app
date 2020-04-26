package com.example.multiplechoicequestion.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multiplechoicequestion.R;
import com.example.multiplechoicequestion.room.CategoricalQuestion;
import com.example.multiplechoicequestion.view.activity.CategoryActivity;
import com.example.multiplechoicequestion.view.adapter.AnswerListAdapter;
import com.example.multiplechoicequestion.view.viewmodel.QuestionViewModel;

import java.util.List;

public class AnswerFragment extends Fragment {

    final String CAREGORY_INDEX = "categoryIndex";
    private QuestionViewModel mViewModel;
    private AnswerListAdapter adapater;
    private TextView textView;
    private int mCategoryIndex = 0;

    public static AnswerFragment newInstance() {
        return new AnswerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.answer_fragment, container, false);
        textView = view.findViewById(R.id.title);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        adapater = new AnswerListAdapter(getContext());
        recyclerView.setAdapter(adapater);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView.setText("Solutions");
        if (savedInstanceState == null) {
            mCategoryIndex = ((CategoryActivity) getActivity()).categoryID;
        } else {
            mCategoryIndex = savedInstanceState.getInt(CAREGORY_INDEX);
        }
        mViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        mViewModel.getAllCategoricalQuestions().observe(getViewLifecycleOwner(), new Observer<List<CategoricalQuestion>>() {
            @Override
            public void onChanged(List<CategoricalQuestion> questionList) {
                adapater.setQuestions(questionList.get(mCategoryIndex).questionList);
            }
        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putInt(CAREGORY_INDEX, mCategoryIndex);

    }
}
