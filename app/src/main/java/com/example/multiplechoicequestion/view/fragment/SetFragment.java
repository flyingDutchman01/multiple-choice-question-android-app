package com.example.multiplechoicequestion.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multiplechoicequestion.R;
import com.example.multiplechoicequestion.room.Category;
import com.example.multiplechoicequestion.view.activity.CategoryActivity;
import com.example.multiplechoicequestion.view.adapter.SetListAdapter;
import com.example.multiplechoicequestion.view.viewmodel.QuestionViewModel;

import java.util.List;

public class SetFragment extends Fragment {

    private QuestionViewModel mViewModel;
    private SetListAdapter adapater;
    private int mCategoryIndex = 0;

    public static SetFragment newInstance() {
        return new SetFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment, container, false);

        mCategoryIndex = ((CategoryActivity) getActivity()).categoryID;

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        adapater = new SetListAdapter(getContext());
        recyclerView.setAdapter(adapater);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        System.out.println("sdfsdfdsf\n\n\n\n");
        mViewModel.getAllCategories().observe(getActivity(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                System.out.println("eheheh\n\n" + categories.get(mCategoryIndex).getSetAmount());
                adapater.setCategory(categories.get(mCategoryIndex));
            }
        });

    }

}
