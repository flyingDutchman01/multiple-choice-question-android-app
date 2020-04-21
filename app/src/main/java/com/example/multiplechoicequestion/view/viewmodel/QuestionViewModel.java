package com.example.multiplechoicequestion.view.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.multiplechoicequestion.room.Question;
import com.example.multiplechoicequestion.room.QuestionRepository;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {

    private QuestionRepository mRepository;

    private LiveData<List<Question>> mAllWords;

    public QuestionViewModel(@NonNull Application application) {
        super(application);

        mRepository = new QuestionRepository(application);
        mAllWords = mRepository.getAllQuestions();
        //System.out.println("size: " + mAllWords.getValue().size());
    }

    public LiveData<List<Question>> getAllWords() {
        if(mAllWords == null){
             mAllWords = mRepository.getAllQuestions();
        }
        return  mAllWords;
    }

    public void insert(Question word) { mRepository.insert(word);}
}
