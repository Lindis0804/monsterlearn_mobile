package com.ldh.monsterlearn.View.Log;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ldh.monsterlearn.API.ApiService;
import com.ldh.monsterlearn.Model.Result;
import com.ldh.monsterlearn.Model.User;
import com.ldh.monsterlearn.Model.UserVerify;
import com.ldh.monsterlearn.R;
import com.ldh.monsterlearn.View.Homepage.Homepage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Verify extends AppCompatActivity {
    private ImageButton imageButton_back;
    private Button button_ok;
    private EditText editText_otp;
    private Intent intent;
    private User user;
    private UserVerify userVerify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        imageButton_back = findViewById(R.id.verify_button_back);
        button_ok = findViewById(R.id.verify_button_ok);
        editText_otp = findViewById(R.id.verify_editText_otp);
        intent = getIntent();
        user = (User) intent.getSerializableExtra("User Information");
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify();
            }
        });

    }
    public void verify(){
        String otp = editText_otp.getText().toString();
        userVerify = new UserVerify(user.getName(),user.getUsername(),user.getPassword(),user.getGmail(),user.getPhone(),otp);
        ApiService.apiService.getVerifyResult(userVerify).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                String t;
                 Result res = response.body();
                 t = res.getResult();
                 if (t.equals("You use an Expired OTP!")){
                     Toast.makeText(Verify.this, t, Toast.LENGTH_SHORT).show();
                 }
                 else if (t.equals("OTP was wrong!!")){
                     Toast.makeText(Verify.this, t, Toast.LENGTH_SHORT).show();
                 }
                 else if (t.equals("Register successfully!!")){
                     Intent intent = new Intent(getApplicationContext(), Homepage.class);
                     intent.putExtra("UserInformation",user);
                     startActivity(intent);
                 }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
               Log.v("TAG","Error in verifying");
            }
        });
    }
}