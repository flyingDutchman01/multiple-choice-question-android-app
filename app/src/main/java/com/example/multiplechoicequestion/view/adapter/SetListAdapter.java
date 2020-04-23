package com.example.multiplechoicequestion.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multiplechoicequestion.R;
import com.example.multiplechoicequestion.room.Category;
import com.example.multiplechoicequestion.view.activity.CategoryActivity;
import com.example.multiplechoicequestion.view.activity.QuizActivity;

import static com.example.multiplechoicequestion.view.activity.QuizActivity.CATEGORY_ID;
import static com.example.multiplechoicequestion.view.activity.QuizActivity.SET_NR;

public class SetListAdapter extends RecyclerView.Adapter<SetListAdapter.SetViewHolder> {

    private final LayoutInflater mInflater;
    private Category mCategory;
    private CategoryActivity activity;

    public SetListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        activity = (CategoryActivity) context;
    }

    @NonNull
    @Override
    public SetListAdapter.SetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.question_recyclerview_item, parent, false);
        return new SetViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SetListAdapter.SetViewHolder holder, int position) {

        if (mCategory != null) {
            holder.wordItemView.setText("Set " + (position + 1));
        } else {
            holder.wordItemView.setText("No Word");
        }

        holder.wordItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setNr = position + 1;
                Intent i = new Intent(activity, QuizActivity.class);
                i.putExtra(CATEGORY_ID, activity.categoryID);
                i.putExtra(SET_NR, activity.setNr);
                activity.startActivity(i);
            }
        });
    }

    public void setCategory(Category category) {
        mCategory = category;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mCategory != null)
            return mCategory.getSetAmount();
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