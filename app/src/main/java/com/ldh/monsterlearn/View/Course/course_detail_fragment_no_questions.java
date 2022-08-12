package com.ldh.monsterlearn.View.Course;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.ldh.monsterlearn.R;

public class course_detail_fragment_no_questions extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_detail_fragment_no_questions,container,false);
        return view;
    }
}
