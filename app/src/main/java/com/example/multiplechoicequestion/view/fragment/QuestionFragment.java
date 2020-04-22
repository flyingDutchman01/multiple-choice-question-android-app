/*
package com.example.multiplechoicequestion.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multiplechoicequestion.R;
import com.example.multiplechoicequestion.room.Question;
import com.example.multiplechoicequestion.view.adapter.QuestionListAdapter;
import com.example.multiplechoicequestion.view.viewmodel.QuestionViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static android.app.Activity.RESULT_OK;
public class QuestionFragment extends Fragment {

    private QuestionViewModel mViewModel;
    public static final int REQUEST_CODE_WORD = 1;
    QuestionListAdapter adapater;

    public static QuestionFragment newInstance() {
        return new QuestionFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        adapater = new QuestionListAdapter(getContext());
        recyclerView.setAdapter(adapater);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuizActivity.class);
                startActivityForResult(intent, REQUEST_CODE_WORD);
            }
        });
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        mViewModel.getAllWords().observe(getActivity(), new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                adapater.setQuestions(questions);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_WORD && resultCode == RESULT_OK){
                Question word = new Question(data.getStringExtra(EXTRA_REPLY));
                mViewModel.insert(word);
        }else{
            Toast.makeText(getContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }

    }
}


*/
