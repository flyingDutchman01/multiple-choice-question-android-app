package com.example.multiplechoicequestion.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface CategoricalQuestionDao {

    @Transaction
    @Query("SELECT * FROM  category_table  WHERE id = :categoryId + :setNr  ")
    LiveData<List<CategoricalQuestion>> getCategoricalQuestions(int categoryId, int setNr);

    @Transaction
    @Query("SELECT * FROM  category_table")
    LiveData<List<CategoricalQuestion>> getCategoricalQuestions();

}
