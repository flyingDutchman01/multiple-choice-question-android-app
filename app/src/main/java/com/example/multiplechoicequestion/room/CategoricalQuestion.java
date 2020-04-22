package com.example.multiplechoicequestion.room;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoricalQuestion {
    @Embedded
    public Category category;

    @Relation(parentColumn = "id",
            entityColumn = "category_id", entity = Question.class)
    public List<Question> questionList;
}
