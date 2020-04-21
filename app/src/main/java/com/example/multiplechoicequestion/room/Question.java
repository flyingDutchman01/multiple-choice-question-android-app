package com.example.multiplechoicequestion.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "question_table")
public class Question {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "question")
    private String mQuestion;

    @NonNull
    @ColumnInfo(name = "option1")
    private String mOption1;

    @NonNull
    @ColumnInfo(name = "option2")
    private String mOption2;

    @NonNull
    @ColumnInfo(name = "option3")
    private String mOption3;

    @NonNull
    @ColumnInfo(name = "option4")
    private String mOption4;

    @NonNull
    @ColumnInfo(name = "answer_nr")
    private int mAnswerNr;

    @NonNull
    @ColumnInfo(name = "category_id")
    private int mCategoryId;

    public void setCategoryId(int mCategoryId) {
        this.mCategoryId = mCategoryId;
    }

    @Ignore
    public Question(@NonNull String mQuestion, @NonNull String mOption1, @NonNull String mOption2, @NonNull String mOption3, @NonNull String mOption4, @NonNull int mAnswerNr, @NonNull int mCategoryId) {
        this.mQuestion = mQuestion;
        this.mOption1 = mOption1;
        this.mOption2 = mOption2;
        this.mOption3 = mOption3;
        this.mOption4 = mOption4;
        this.mAnswerNr = mAnswerNr;
        this.mCategoryId = mCategoryId;
    }

    public Question(@NonNull String mQuestion, @NonNull String mOption1, @NonNull String mOption2, @NonNull String mOption3, @NonNull String mOption4, int mAnswerNr) {
        this.mQuestion = mQuestion;
        this.mOption1 = mOption1;
        this.mOption2 = mOption2;
        this.mOption3 = mOption3;
        this.mOption4 = mOption4;
        this.mAnswerNr = mAnswerNr;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getQuestion() {
        return mQuestion;
    }

    @NonNull
    public String getOption1() {
        return mOption1;
    }

    @NonNull
    public String getOption2() {
        return mOption2;
    }

    @NonNull
    public String getOption3() {
        return mOption3;
    }

    @NonNull
    public String getOption4() {
        return mOption4;
    }

    @NonNull
    public int getAnswerNr() {
        return mAnswerNr;
    }

    @NonNull
    public int getCategoryId() {
        return mCategoryId;
    }


}