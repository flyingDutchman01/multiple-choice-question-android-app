package com.example.multiplechoicequestion.view.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.multiplechoicequestion.room.CategoricalQuestion;
import com.example.multiplechoicequestion.room.Category;
import com.example.multiplechoicequestion.room.Question;
import com.example.multiplechoicequestion.room.QuestionRepository;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {
    private QuestionRepository mRepository;

    private LiveData<List<Question>> mAllQuestion;
    private LiveData<List<Category>> mAllCategory;
    private LiveData<List<CategoricalQuestion>> mAllCategoricalQuestion;

    public QuestionViewModel(@NonNull Application application) {
        super(application);

        mRepository = new QuestionRepository(application);
        mAllQuestion = mRepository.getAllQuestions();
        mAllCategory = mRepository.getAllCategories();
        mAllCategoricalQuestion = mRepository.getAllCategoricalQuestions();
        //System.out.println("size: " + mAllWords.getValue().size());
    }

    public LiveData<List<Question>> getAllQuestions() {
        if (mAllQuestion == null) {
            mAllQuestion = mRepository.getAllQuestions();
        }
        return mAllQuestion;
    }

    public LiveData<List<Category>> getAllCategories() {
        if (mAllCategory == null) {
            mAllCategory = mRepository.getAllCategories();
        }
        return mAllCategory;
    }

    public LiveData<List<CategoricalQuestion>> getAllCategoricalQuestions() {
        if (mAllCategory == null) {
            mAllCategoricalQuestion = mRepository.getAllCategoricalQuestions();
        }
        return mAllCategoricalQuestion;
    }

    public LiveData<List<CategoricalQuestion>> getAllCategoricalQuestions(int categoryId, int setNr) {
        return mRepository.getAllCategoricalQuestions(categoryId, setNr);
    }


    public void insert(Question question) {
        mRepository.insert(question);
    }

    public void insert(Category category) {
        mRepository.insert(category);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public LiveData<Integer> getQuestionRowCount() {
        return mRepository.getQuestionRowCount();
    }

    public LiveData<Integer> getQuestionRowCount(int categoryId) {
        return mRepository.getQuestionRowCount(categoryId);
    }
}