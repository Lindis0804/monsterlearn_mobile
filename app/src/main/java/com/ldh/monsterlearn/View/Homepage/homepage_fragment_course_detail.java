package com.ldh.monsterlearn.View.Homepage;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ldh.monsterlearn.API.ApiService;
import com.ldh.monsterlearn.Model.CourseUpdate;
import com.ldh.monsterlearn.Model.Question;
import com.ldh.monsterlearn.Model.Result;
import com.ldh.monsterlearn.Model.User;
import com.ldh.monsterlearn.Model.HomepageCourse;
import com.ldh.monsterlearn.R;
import com.ldh.monsterlearn.View.Course.course_detail;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class homepage_fragment_course_detail extends Fragment {
//    Button button_luyen_tap,button_luyen_thi;
    private Homepage homepage;
    private User user;
    private HomepageCourse course;
    private TextView txt1,txt2;
    private ProgressBar p1,p2;
    private LinearLayout button_luyen_tap,button_luyen_thi;
    private Button button_rate;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_fragment_course_detail,container,false);
        button_luyen_tap = view.findViewById(R.id.homepage_fragment_course_detail_button_luyen_tap);
        button_luyen_thi = view.findViewById(R.id.homepage_fragment_course_detail_button_luyen_thi);
        txt1 = view.findViewById(R.id.homepage_fragment_course_detail_textview_course_completed);
        txt2 = view.findViewById(R.id.homepage_fragment_course_detail_textview_challenge_completed);
        p1 = view.findViewById(R.id.homepage_fragment_course_detail_progressbar_course_completed);
        p2 = view.findViewById(R.id.homepage_fragment_course_detail_progressbar_challenge_completed);
        button_rate = view.findViewById(R.id.homepage_fragment_course_detail_button_rate);
        homepage = (Homepage) getActivity();
        user = homepage.getUser();
        course = homepage.getCourse();
        int numOfCourse = user.getCourseList().size();
        int numOfChallenge = user.getNumOfPassedChallenge(course.getHomepage_course_name());
        int maxNumOfChallenge = course.getNumOfQuestion();
        txt1.setText(numOfCourse+"/4");
        txt2.setText(numOfChallenge+"/"+maxNumOfChallenge);
        p1.setMax(4);
        p1.setProgress(numOfCourse,true);
        p2.setMax(maxNumOfChallenge);
        p2.setProgress(numOfChallenge,true);
        button_luyen_tap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), course_detail.class);
                goToCourseDetail(course.getHomepage_course_name());
            }
        });
        button_luyen_thi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),course_detail.class);
                startActivity(intent);
            }
        });
        button_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogRate();
            }
        });
        return view;
    }
    public void goToCourseDetail(String courseName){
        Intent intent = new Intent(getContext(),course_detail.class);
        ApiService.apiService.getQuestionList(courseName).enqueue(new Callback<ArrayList<Question>>() {
            @Override
            public void onResponse(Call<ArrayList<Question>> call, Response<ArrayList<Question>> response) {
                Toast.makeText(getContext(), "Success get questionList", Toast.LENGTH_SHORT).show();
                ArrayList<Question> quesList = new ArrayList<>();
                quesList = response.body();
                intent.putExtra("Question List",quesList);
                intent.putExtra("User Information",homepage.getUser());
                intent.putExtra("Num Of Question",course.getNumOfQuestion());
                intent.putExtra("Course Name",course.getHomepage_course_name());
               // Toast.makeText(getContext(), quesList.toString(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<ArrayList<Question>> call, Throwable t) {
                Toast.makeText(getContext(), "Fail get questionList", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void openDialogRate(){
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.homepage_fragment_course_detail_dialog_rate);
        dialog.setCanceledOnTouchOutside(false);
        ImageButton imageButton_back = dialog.findViewById(R.id.rate_imageButton_back);
        TextView textView_point = dialog.findViewById(R.id.rate_textView_point);
        SeekBar seekBar_rate = dialog.findViewById(R.id.rate_seekBar_rate);
        Button button_ok = dialog.findViewById(R.id.rate_button_ok);
        final float[] d = new float[1];
        seekBar_rate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                d[0] = ((float) i/seekBar.getMax()) * 5;
                textView_point.setText(d[0] +"/5");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
               Log.v("TAG","Start");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               Log.v("TAG","End");
            }
        });
        imageButton_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numOfRate = course.getNumOfRate()+1;
                float rate = (course.getRate()*(numOfRate-1)+d[0])/numOfRate;
                CourseUpdate courses = new CourseUpdate(rate,numOfRate);
                ApiService.apiService.updateCourse(course.getHomepage_course_name(),courses).enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        Result res = response.body();
                        String s = res == null?"0":res.getResult();
                        if (s.equals("1")){
                            Toast.makeText(homepage, "Rate successfully!!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(homepage, "Failure in rating!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Toast.makeText(homepage, "Rate :Error in getting api!!", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
