package com.example.multiplechoicequestion.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multiplechoicequestion.R;
import com.example.multiplechoicequestion.room.Category;
import com.example.multiplechoicequestion.view.adapter.CategoryListAdapter;
import com.example.multiplechoicequestion.view.viewmodel.QuestionViewModel;

import java.util.List;

public class CategoryFragment extends Fragment {

    private QuestionViewModel mViewModel;
    private CategoryListAdapter adapater;
    //  private ToggleButton toggleButton;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.category_fragment, container, false);
        //  toggleButton = view.findViewById(R.id.toggleButton);
        adapater = new CategoryListAdapter(getContext(), getParentFragmentManager());

        Bundle b = getArguments();//taking arguments from startingFragment
        int value = b.getInt("radio");
        adapater.setChecked(value);//transfering value


        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        recyclerView.setAdapter(adapater);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                adapater.setChecked(isChecked);
//            }
//        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        mViewModel.getAllCategories().observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                adapater.setCategories(categories);
            }
        });

    }

}
