package com.ldh.monsterlearn.View.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ldh.monsterlearn.API.ApiService;
import com.ldh.monsterlearn.Model.User;
import com.ldh.monsterlearn.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeProfile extends AppCompatActivity {
    EditText editText_name,editText_address,editText_contact,editText_birthday,editText_workAt,editText_about;
    private User user;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("User Information");
        if (user != null) {
            editText_name = findViewById(R.id.change_profile_editText_name);
            editText_address = findViewById(R.id.change_profile_editText_address);
            editText_contact = findViewById(R.id.change_profile_editText_contact);
            editText_birthday = findViewById(R.id.change_profile_editText_birthday);
            editText_workAt = findViewById(R.id.change_profile_editText_workAt);
            editText_about = findViewById(R.id.change_profile_editText_about);
            button = findViewById(R.id.change_profile_button_ok);
            editText_name.setText(user.getName());
            editText_address.setText(user.getAddress());
            editText_contact.setText(user.getPhone());
            editText_birthday.setText(user.getBirthday());
            editText_workAt.setText(user.getWorkPlace());
            editText_about.setText(user.getAbout());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    user.setName(editText_name.getText().toString());
                    user.setAddress(editText_address.getText().toString());
                    user.setPhone(editText_contact.getText().toString());
                    user.setBirthday(editText_birthday.getText().toString());
                    user.setWorkPlace(editText_workAt.getText().toString());
                    user.setAbout(editText_about.getText().toString());
                    ApiService.apiService.updateInformation(user).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Toast.makeText(ChangeProfile.this, "Update Information Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(ChangeProfile.this, "Failure in updating", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
    public void changeHeight(View view){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        view.setLayoutParams(params);
    }
}