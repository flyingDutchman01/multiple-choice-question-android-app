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

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Question> mWords;

    public QuestionListAdapter(Context context){ mInflater = LayoutInflater.from(context);    }

    @NonNull
    @Override
    public QuestionListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.question_recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionListAdapter.WordViewHolder holder, int position) {
        if(mWords != null){
            Question current = mWords.get(position);
            holder.wordItemView.setText(current.getQuestion());
        }else{
            holder.wordItemView.setText("No Word");
        }
    }

    public void setWords(List<Question> words){
        mWords = words;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if(mWords != null)
            return mWords.size();
        else return 0;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {

        private final TextView wordItemView;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            this.wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}
