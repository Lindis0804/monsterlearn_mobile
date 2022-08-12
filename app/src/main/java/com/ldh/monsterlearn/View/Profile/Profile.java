package com.ldh.monsterlearn.View.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldh.monsterlearn.API.ApiService;
import com.ldh.monsterlearn.Model.User;
import com.ldh.monsterlearn.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {
    private ImageButton imageButton_back,imageButton_setting,imageButton_add_person;
    private ImageView imageView_avatar;
    private TextView textView_name;
    private TextView textView_address,textView_followers,textView_courses,textView_coins;
    private Button button_fix_profile;
    private TextView textView_work_place,textView_specialization,textView_birthday,textView_about,textView_contact;
    private String username;
    private User user;
    private boolean isChange = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imageButton_back = findViewById(R.id.profile_imageButton_back);
        imageButton_setting = findViewById(R.id.profile_imageButton_setting);
        imageButton_add_person = findViewById(R.id.profile_imageButton_add_person);
        imageView_avatar = findViewById(R.id.profile_imageView_avatar);
        textView_name = findViewById(R.id.profile_textView_name);
        textView_address = findViewById(R.id.profile_textView_address);
        textView_followers = findViewById(R.id.profile_textView_followers);
        textView_courses = findViewById(R.id.profile_textView_courses);
        textView_coins = findViewById(R.id.profile_textView_coins);
        button_fix_profile = findViewById(R.id.profile_button_fix_profile);
        textView_work_place = findViewById(R.id.profile_textView_work_place);
        textView_specialization = findViewById(R.id.profile_textView_specialization);
        textView_birthday = findViewById(R.id.profile_textView_birthday);
        textView_about = findViewById(R.id.profile_textView_about);
        textView_contact = findViewById(R.id.profile_textView_contact);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        if (isChange) {
            isChange = false;
            getUserInformation();
        }
        imageButton_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void getUserInformation(){
        ApiService.apiService.getUserInformation(username).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                if (user != null) {
                    textView_name.setText(user.getName());
                    textView_address.setText(user.getAddress());
                    textView_followers.setText(user.getFollowers() + "");
                    textView_coins.setText(user.getCoins() + "");
                    textView_work_place.setText(user.getWorkPlace());
                    textView_specialization.setText(user.getSpecialization());
                    textView_birthday.setText(user.getBirthday());
                    textView_about.setText(user.getAbout());
                    textView_contact.setText(user.getPhone());
                }
                button_fix_profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goToChangeProfile(user);
                    }
                });
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.v("TAG","Error in getting data");
            }
        });
    }
    public void goToChangeProfile(User user){
        Intent intent = new Intent(getApplicationContext(),ChangeProfile.class);
        intent.putExtra("User Information",user);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        isChange = true;
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (isChange){
            isChange = false;
            getUserInformation();
        }
        super.onResume();
    }
}