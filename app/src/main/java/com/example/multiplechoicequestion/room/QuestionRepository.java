package com.example.multiplechoicequestion.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionRepository {

    private QuestionDao mQuestionDao;
    private CategoricalQuestionDao mCategoricalQuestionDao;
    private CategoryDao mCategoryDao;
    private LiveData<List<Question>> mAllQuestion;
    private LiveData<List<CategoricalQuestion>> mAllCategoricalQuestion;
    private LiveData<List<Category>> mAllCategories;

    public QuestionRepository(Application application) {
        QuestionRoomDatabase db = QuestionRoomDatabase.getDatabase(application);
        mQuestionDao = db.questionDao();
        mCategoricalQuestionDao = db.categoricalQuestionDao();
        mCategoryDao = db.categoryDao();
        mAllQuestion = mQuestionDao.getAllQuestions();
        mAllCategories = mCategoryDao.getAllCategory();
        mAllCategoricalQuestion = mCategoricalQuestionDao.getCategoricalQuestions();
    }

    public LiveData<List<Question>> getAllQuestions() {
        return mAllQuestion;
    }

    public LiveData<List<Category>> getAllCategories() {
        return mAllCategories;
    }

    public LiveData<List<CategoricalQuestion>> getAllCategoricalQuestions() {
        return mAllCategoricalQuestion;
    }

    public LiveData<List<CategoricalQuestion>> getAllCategoricalQuestions(int categoryId, int setNr) {
        return mCategoricalQuestionDao.getCategoricalQuestions(categoryId, setNr);
    }

    public void insert(Question question){
        QuestionRoomDatabase.databaseWriteExecutor.execute(() -> {
            mQuestionDao.insert(question);
        });
    }

    public void insert(Category category) {
        QuestionRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCategoryDao.insert(category);
        });
    }

    public void deleteAll() {
        QuestionRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCategoryDao.deleteAll();
            mQuestionDao.deleteAll();
        });
    }
}
