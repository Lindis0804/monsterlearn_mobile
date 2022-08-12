package com.ldh.monsterlearn.View.Log;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ldh.monsterlearn.API.ApiService;
import com.ldh.monsterlearn.Model.UserCourse;
import com.ldh.monsterlearn.View.Homepage.Homepage;
import com.ldh.monsterlearn.Model.User;
import com.ldh.monsterlearn.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login_fragment_login extends Fragment {
    private EditText username,password;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.login_login,container,false);
        username = view.findViewById(R.id.login_login_editText_username);
        password = view.findViewById(R.id.login_login_editText_password);
        view.findViewById(R.id.login_login_button_log_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getContext(),Homepage.class));
                String a = username.getText().toString();
                String b = password.getText().toString();
                User user = new User(a,b);
                ApiService.apiService.getLogInResult(user).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                        if (response.body() != null){
                            User userInformation = response.body();
                            userInformation.setCourseMap();
                            Log.v("TAG","course list :"+userInformation.getCourseList().size());
                            for (UserCourse x:userInformation.getCourseList()){
                                Log.v("TAG",x.getCourse()+" "+x.getNumOfQuestion()+"\n");
                            }
                            Intent intent = new Intent(getActivity(), Homepage.class);
                            intent.putExtra("UserInformation",userInformation);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getContext(), "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.v("TAG",t.getMessage());
                        Toast.makeText(getContext(), "Error in getting api", Toast.LENGTH_SHORT).show();
                    }
                });
                
            }
        });


        return view;
    }
}
