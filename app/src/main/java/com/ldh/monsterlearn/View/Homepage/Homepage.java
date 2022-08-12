package com.ldh.monsterlearn.View.Homepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.ldh.monsterlearn.API.ApiService;
import com.ldh.monsterlearn.Model.HomepageCourse;
import com.ldh.monsterlearn.Model.User;
import com.ldh.monsterlearn.R;
import com.ldh.monsterlearn.View.Profile.Profile;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Homepage extends AppCompatActivity implements homepage_fragment_learn.SendDataListener, homepage_course_clickListener {
   private ChipNavigationBar chipNavigationBar;
   private FrameLayout frameLayout;
   private Button button_coins;
   private User user;
   private HomepageCourse course;
   private ImageButton imageButtonArrowDown;
   private ConstraintLayout topSlide;
   private RecyclerView topSlideRecyclerView;
   private ArrayList<HomepageCourse> courses = new ArrayList<>();
   private boolean openTopSlide = false;
   private TextView textViewCourse;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        //nhận dữ liệu từ activity trước gửi đến (dữ liệu về user)
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("UserInformation");
        //
        chipNavigationBar = findViewById(R.id.homepage_chipNavigationBar);
        frameLayout = findViewById(R.id.homepage_frameLayout);
        imageButtonArrowDown = findViewById(R.id.homepage_imageButton_arrow_down);
        topSlide = findViewById(R.id.homepage_top_slide);
        topSlideRecyclerView = findViewById(R.id.homepage_top_slide_recyclerview);
        textViewCourse = findViewById(R.id.homepage_textview_course);
        //khi vào trang chủ thì hiển thị trang fragment_learn là mặc định
        getFragmentManager().beginTransaction().replace(R.id.homepage_frameLayout,new homepage_fragment_learn()).commit();
        //cài đặt click listener cho bottom bar
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                switch (i){
                    case R.id.homepage_nav_learn:
                        Toast.makeText(Homepage.this, "Learn", Toast.LENGTH_SHORT).show();
                        fragment = new homepage_fragment_learn();
                        fragmentTransaction.replace(R.id.homepage_frameLayout,fragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.homepage_nav_profile:
                        Intent intent1 = new Intent(getApplicationContext(),Profile.class);
                        intent1.putExtra("username",user.getUsername());
                        startActivity(intent1);
                        break;

                }
            }
        });
        //hiển thị thông tin coins
        button_coins = findViewById(R.id.homepage_button_coins);
        button_coins.setText(user.getCoins()+"");

        //ấn để hiển thị top slide
        imageButtonArrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTopSlide = !openTopSlide;
                if (openTopSlide) {
                    topSlideRecyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                            getApplicationContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false
                    );
                    topSlideRecyclerView.setLayoutManager(layoutManager);
                    //lấy dữ liệu khóa học rồi render ra
                    renderCourseList();
                    Animation animationDown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
                    topSlide.bringToFront();
                    topSlide.startAnimation(animationDown);
                    topSlide.setVisibility(View.VISIBLE);
                }
                else{
                    Animation animationUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
                    topSlide.startAnimation(animationUp);
                    topSlide.setVisibility(View.INVISIBLE);
                }

            }
        });
    }

    public User getUser() {
        return user;
    }
    public HomepageCourse getCourse(){
        return course;
    }
    @Override
    public void sendData(HomepageCourse course) {
          this.course = course;
          TextView textView = findViewById(R.id.homepage_textview_course);
          textView.setText(course.getHomepage_course_name());
    }
    public void renderCourseList(){
        if (courses.size() == 0) {
            ApiService.apiService.getListCourse().enqueue(new Callback<ArrayList<HomepageCourse>>() {
                @Override
                public void onResponse(Call<ArrayList<HomepageCourse>> call, Response<ArrayList<HomepageCourse>> response) {
                    courses = response.body();
                    courses.get(0).setHomepage_course_image(R.drawable.homepage_raw_android);
                    courses.get(1).setHomepage_course_image(R.drawable.homepage_raw_computer_network);
                    courses.get(2).setHomepage_course_image(R.drawable.homepage_raw_distributed_system);
                    homepage_course_adapter adapter = new homepage_course_adapter(courses, Homepage.this);
                    topSlideRecyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<ArrayList<HomepageCourse>> call, Throwable t) {
                    Log.v("TAG", "Fragment learn" + t.getMessage());
                    Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            homepage_course_adapter adapter = new homepage_course_adapter(courses, Homepage.this);
            topSlideRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClick(int position) {
        this.course = courses.get(position);
        getFragmentManager().beginTransaction().replace(R.id.homepage_frameLayout,new homepage_fragment_course_detail()).commit();
        openTopSlide =!openTopSlide;
        Animation animationUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
        textViewCourse.setText(course.getHomepage_course_name());
        topSlide.startAnimation(animationUp);
        topSlide.setVisibility(View.INVISIBLE);
   }
}