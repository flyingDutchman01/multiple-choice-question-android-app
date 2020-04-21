package com.example.multiplechoicequestion.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionRepository {

    private QuestionDao mQuestionDao;
    private LiveData<List<Question>> mAllQuestion;

    public QuestionRepository(Application application) {
        QuestionRoomDatabase db = QuestionRoomDatabase.getDatabase(application);
        mQuestionDao = db.questionDao();
        mAllQuestion = mQuestionDao.getAllQuestions();
    }

    public LiveData<List<Question>> getAllQuestions() {
        return mAllQuestion;
    }

    public void insert(Question question){
        QuestionRoomDatabase.databaseWriteExecutor.execute(() -> {
            mQuestionDao.insert(question);
        });
    }
}
