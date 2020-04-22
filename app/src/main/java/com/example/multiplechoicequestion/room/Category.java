package com.example.multiplechoicequestion.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category_table")
public class Category {
    @PrimaryKey
    int id;

    @ColumnInfo(name = "category_name")
    String name;

    @ColumnInfo(name = "set_amount")
    int setAmount;

    public Category(int id, String name, int setAmount) {
        this.id = id;
        this.name = name;
        this.setAmount = setAmount;
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return name;
    }

    public int getSetAmount() {
        return setAmount;
    }
}


