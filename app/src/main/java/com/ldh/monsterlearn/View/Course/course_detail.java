package com.ldh.monsterlearn.View.Course;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.ldh.monsterlearn.API.ApiService;
import com.ldh.monsterlearn.Model.Question;
import com.ldh.monsterlearn.Model.User;
import com.ldh.monsterlearn.Model.UserCourse;
import com.ldh.monsterlearn.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class course_detail extends AppCompatActivity {
    public int count = 0;
    private int backCount;
    private static ArrayList<Question> quesList ;
    private User user;
    private int numOfQuestion;
    private String courseName;
    private ProgressBar progressBar;
    private Handler handler = new Handler();
    private int correctAnswer;
    private int coins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_detail);
        //lấy thông tin người dùng==================================================
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("User Information");
        quesList = (ArrayList<Question>) intent.getSerializableExtra("Question List");
        numOfQuestion = intent.getIntExtra("Num Of Question",0);
        courseName = intent.getStringExtra("Course Name");
        Log.v("TAG","courseName :"+courseName);
        coins = user.getCoins();
        count = user.getNumOfPassedChallenge(courseName);
        backCount = count;
        Log.v("TAG","count :"+count);
        progressBar = findViewById(R.id.course_detail_progressBar_solving_process);
        //render thông tin quá trình học ra progress bar
        progressBar.setMax(numOfQuestion);
        Log.v("TAG","Num of question "+numOfQuestion);
        progressBar.setProgress(backCount);
        //render giao diện câu hỏi
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        final course_detail_fragment_questions[] fragment_questions = {new course_detail_fragment_questions()};
        fragmentTransaction.replace(R.id.course_detail_framelayout, fragment_questions[0]);
        fragmentTransaction.commit();

        //nếu trả lời đúng thì chuyển câu hỏi
        findViewById(R.id.course_detail_button_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(course_detail.this, "Clicked!!", Toast.LENGTH_SHORT).show();
                correctAnswer = fragment_questions[0].correctAnswer();
                boolean check = fragment_questions[0].check();
                if (backCount <count){
                    backCount++;
                    setProgressBar(backCount);
                    fragment_questions[0] = new course_detail_fragment_questions();
                    FragmentTransaction f1 = getFragmentManager().beginTransaction();
                    f1.replace(R.id.course_detail_framelayout, fragment_questions[0]);
                    f1.commit();
                }
                else if (check) {
                    ++count;
                    backCount = count;
                    setProgressBar(backCount);
                    coins += 10;
                    if (count <numOfQuestion) {
                        Log.v("TAG","count "+count+" num of question "+numOfQuestion);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                fragment_questions[0] = new course_detail_fragment_questions();
                                FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
                                fragmentTransaction1.replace(R.id.course_detail_framelayout, fragment_questions[0]);
                                fragmentTransaction1.commit();
                            }
                        }, 1000);
                    }
                    else{
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                course_detail_fragment_no_questions fragment = new course_detail_fragment_no_questions();
                                FragmentTransaction fragmentTransaction2 = getFragmentManager().beginTransaction();
                                fragmentTransaction2.replace(R.id.course_detail_framelayout, fragment);
                                fragmentTransaction2.commit();
                            }
                        },1000);
                    }
                }
            }
        });
        findViewById(R.id.course_detail_button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backCount--;
                if (backCount>=0){
                    setProgressBar(backCount);
                    fragment_questions[0] = new course_detail_fragment_questions();
                    FragmentTransaction f2 = getFragmentManager().beginTransaction();
                    f2.replace(R.id.course_detail_framelayout, fragment_questions[0]);
                    f2.commit();
                }
            }
        });
    }
    //lấy câu hỏi từ database===============================================
    public void getQuestionList(String course){

    }
    public Question getQuestion(int c){
        return quesList.get(c);
    }
    public ArrayList<Question> getQuesList(){
        return quesList;
    }
    public int getCount(){
        return backCount;
    }
    public boolean isBack(){
        return backCount < count;
    }

    @Override
    protected void onPause() {
        user.setCoins(coins);
        user.setNumOfPassedChallenged(courseName,count);
        for (UserCourse x:user.getCourseList()){
            Log.v("TAG",x.getCourse()+" "+x.getNumOfQuestion());
        }
        Log.v("TAG","course detail on pause");
        ApiService.apiService.updateInformation(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.v("TAG","Successfully update information");
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.v("TAG","Failure in updating");
            }
        });
        super.onPause();
    }
    public void setProgressBar(int backCount){
        progressBar.setProgress(backCount);
    }
}