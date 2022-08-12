package com.ldh.monsterlearn.View.Course;

import static com.ldh.monsterlearn.R.drawable.course_detail_custom_answer;
import static com.ldh.monsterlearn.R.drawable.course_detail_custom_answer_right;
import static com.ldh.monsterlearn.R.drawable.course_detail_custom_answer_wrong;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ldh.monsterlearn.Model.Question;
import com.ldh.monsterlearn.R;

import java.util.ArrayList;

public class course_detail_fragment_questions extends Fragment {
    private course_detail courseDetail;
    private TextView txt;
    private RadioButton rbA,rbB,rbC,rbD;
    private RadioGroup radioGroup;
    private Question question;
    private int answer;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_detail_fragment_questions,container,false);
        txt = view.findViewById(R.id.course_detail_fragment_questions_textview_questions);
        rbA = view.findViewById(R.id.course_detail_fragment_questions_radiobutton_A);
        rbB = view.findViewById(R.id.course_detail_fragment_questions_radiobutton_B);
        rbC = view.findViewById(R.id.course_detail_fragment_questions_radiobutton_C);
        rbD = view.findViewById(R.id.course_detail_fragment_questions_radiobutton_D);
        radioGroup = view.findViewById(R.id.course_detail_fragment_questions_radiogroup_answers);
        courseDetail = (course_detail) getActivity();
      //  ArrayList<Question> quesList = courseDetail.getQuesList();
        int count = courseDetail.getCount();
        question = courseDetail.getQuestion(count);
        txt.setText(question.getQuestion());
        rbA.setText(question.getA());
        rbB.setText(question.getB());
        rbC.setText(question.getC());
        rbD.setText(question.getD());
        if (courseDetail.isBack()) setAnswer();
        return view;
    }
    public int correctAnswer(){
        if (answer==1 && question.getAnswer().equals("A")) return 1;
        if (answer==2 && question.getAnswer().equals("B")) return 2;
        if (answer==3 && question.getAnswer().equals("C")) return 3;
        if (answer==4 && question.getAnswer().equals("D")) return 4;
        return -1;
    }
    public boolean check(){
        if (rbA.isChecked()) {
            if (question.getAnswer().equals("A")){
                changeCorrect(rbA);
                return true;
            }
            else{
                changeWrong(rbA);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        changeBack(rbA);
                    }
                },1000);
                return false;
            }
        }
        if (rbB.isChecked()) {
            if (question.getAnswer().equals("B")){
                changeCorrect(rbB);
                return true;
            }
            else{
                changeWrong(rbB);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        changeBack(rbB);
                    }
                },1000);
                return false;
            }
        }
        if (rbC.isChecked()) {
            if (question.getAnswer().equals("C")){
                changeCorrect(rbC);
                return true;
            }
            else{
                changeWrong(rbC);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        changeBack(rbC);
                    }
                },1000);
                return false;
            }
        }
        if (rbD.isChecked()) {
            if (question.getAnswer().equals("D")){
                changeCorrect(rbD);
                return true;
            }
            else{
                changeWrong(rbD);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        changeBack(rbD);
                    }
                },1000);
                return false;
            }
        }
        return false;
    }
    public void changeCorrect(RadioButton rb){
        rb.setBackgroundResource(course_detail_custom_answer_right);
        rb.setTextColor(getResources().getColor(R.color.green));
    }
    public void changeWrong(RadioButton rb){
        rb.setBackgroundResource(course_detail_custom_answer_wrong);
        rb.setTextColor(getResources().getColor(R.color.red));
    }
    public void changeBack(RadioButton rb){
        rb.setBackgroundResource(course_detail_custom_answer);
        rb.setTextColor(getResources().getColor(R.color.white));
    }
    public void setAnswer(){
        if (question.getAnswer().equals("A")) {rbA.setChecked(true);changeCorrect(rbA); return;}
        if (question.getAnswer().equals("B")) {rbB.setChecked(true);changeCorrect(rbB);return;}
        if (question.getAnswer().equals("C")) {rbC.setChecked(true);changeCorrect(rbC);return;}
        if (question.getAnswer().equals("D")) {rbD.setChecked(true);changeCorrect(rbD);return;}
    }
}
