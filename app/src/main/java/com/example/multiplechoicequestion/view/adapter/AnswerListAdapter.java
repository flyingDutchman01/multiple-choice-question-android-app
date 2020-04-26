package com.example.multiplechoicequestion.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multiplechoicequestion.R;
import com.example.multiplechoicequestion.room.Question;

import java.util.List;

public class AnswerListAdapter extends RecyclerView.Adapter<AnswerListAdapter.SetViewHolder> {

    private final LayoutInflater mInflater;
    private List<Question> mQuestions;

    public AnswerListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AnswerListAdapter.SetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.question_recyclerview_item, parent, false);
        return new SetViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerListAdapter.SetViewHolder holder, int position) {
        String answer = "";
        switch (mQuestions.get(position).getAnswerNr()) {
            case 1:
                answer = mQuestions.get(position).getOption1();
            case 2:
                answer = mQuestions.get(position).getOption2();
            case 3:
                answer = mQuestions.get(position).getOption3();
            case 4:
                answer = mQuestions.get(position).getOption4();

        }
        if (mQuestions != null) {
            holder.wordItemView.setText(mQuestions.get(position).getQuestion() + "\n" + answer);
        } else {
            holder.wordItemView.setText("No Word");
        }


    }


    public void setQuestions(List<Question> questions) {
        mQuestions = questions;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mQuestions != null)
            return mQuestions.size();
        else return 0;
    }

    class SetViewHolder extends RecyclerView.ViewHolder {

        private final TextView wordItemView;

        public SetViewHolder(@NonNull View itemView) {
            super(itemView);
            this.wordItemView = itemView.findViewById(R.id.textView);

        }
    }
}
