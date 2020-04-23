package com.example.multiplechoicequestion.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Question question);

    @Query("DELETE FROM question_table")
    void deleteAll();

    @Query("SELECT * from question_table ORDER BY question ASC")
    LiveData<List<Question>> getAlphatizedQuestions();

    @Query("SELECT * FROM question_table ORDER BY RANDOM()" )
    LiveData<List<Question>> getRandomQuestions();

    @Query("SELECT * FROM question_table")
    LiveData<List<Question>> getAllQuestions();

    @Query("SELECT COUNT(*) FROM question_table")
    LiveData<Integer> getRowCount();

    @Query("SELECT COUNT(*) FROM question_table WHERE category_id = :categoryId")
    LiveData<Integer> getRowCount(int categoryId);
}
