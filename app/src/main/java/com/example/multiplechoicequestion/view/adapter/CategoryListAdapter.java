package com.example.multiplechoicequestion.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multiplechoicequestion.R;
import com.example.multiplechoicequestion.room.CategoricalQuestion;
import com.example.multiplechoicequestion.room.Category;
import com.example.multiplechoicequestion.room.Question;
import com.example.multiplechoicequestion.view.activity.CategoryActivity;
import com.example.multiplechoicequestion.view.fragment.AnswerFragment;
import com.example.multiplechoicequestion.view.fragment.SetFragment;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder> {

    private final LayoutInflater mInflater;
    CategoryActivity activity;
    private List<Question> mQuestions;
    private List<Category> mCategories;
    private List<CategoricalQuestion> mCategoricalQuestion;
    private FragmentManager fragmentManager;
    private boolean isChecked = true;
    private int value;

    public CategoryListAdapter(Context context, FragmentManager fragmentManager) {
        mInflater = LayoutInflater.from(context);
        this.fragmentManager = fragmentManager;
        this.activity = (CategoryActivity) context;
    }


    @NonNull
    @Override
    public CategoryListAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.question_recyclerview_item, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListAdapter.CategoryViewHolder holder, int position) {
        /*if(mQuestions != null){
            Question current = mQuestions.get(position);
            holder.categoryItemView.setText(current.getQuestion() );
        }else */

        if (mCategories != null) {
            Category current = mCategories.get(position);
            holder.categoryItemView.setText(current.getCategoryName());
        } else { /*if(mCategoricalQuestion != null){
            CategoricalQuestion current = mCategoricalQuestion.get(position);
            String q = "";
            for (Question question: current.questionList) {
                q += question.getQuestion();
                q += "\n";
            }
            holder.categoryItemView.setText(q );
        }
        else{*/
            holder.categoryItemView.setText("No Word");
        }

        holder.categoryItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value == 0) {
                    SetFragment setFragment = new SetFragment();
                    activity.categoryID = position;
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, setFragment)
                            .addToBackStack("Stack")
                            .commit();
                } else {
                    AnswerFragment answerFragment = new AnswerFragment();
                    activity.categoryID = position;
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, answerFragment)
                            .addToBackStack("Stack")
                            .commit();
                }



            }
        });
    }

    public void setQuestions(List<Question> questions) {
        mQuestions = questions;
        notifyDataSetChanged();
    }

    public void setCategories(List<Category> categories) {
        mCategories = categories;
        notifyDataSetChanged();
    }

    public void setCategoricalQuestion(List<CategoricalQuestion> categoricalQuestions) {
        mCategoricalQuestion = categoricalQuestions;
        notifyDataSetChanged();
    }

    //value transferring function
    public void setChecked(int v) {
        value = v;
        Log.i("value456",String.valueOf(v));
    }

    @Override
    public int getItemCount() {
        /*if(mQuestions != null)
            return mQuestions.size();
        else */
        if (mCategories != null)
            return mCategories.size();
        /*else if(mCategoricalQuestion != null)
            return mCategoricalQuestion.size();
        */
        else return 0;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        private final TextView categoryItemView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.categoryItemView = itemView.findViewById(R.id.textView);
        }
    }
}
