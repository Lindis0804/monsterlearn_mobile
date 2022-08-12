package com.ldh.monsterlearn.View.Log;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ldh.monsterlearn.API.ApiService;
import com.ldh.monsterlearn.Model.Result;
import com.ldh.monsterlearn.Model.User;
import com.ldh.monsterlearn.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login_fragment_register extends Fragment {
    private EditText textView_name,textView_username,textView_password,textView_email,textView_phone;
    private Button button_register;
    private User user;
    private String name,username,password,email,phone;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_register,container,false);
        textView_name = view.findViewById(R.id.login_register_textview_name);
        textView_username = view.findViewById(R.id.login_register_textview_username);
        textView_password = view.findViewById(R.id.login_register_textview_password);
        textView_email = view.findViewById(R.id.login_register_textview_email);
        textView_phone = view.findViewById(R.id.login_register_textview_phone);
        button_register = view.findViewById(R.id.login_register_button_register);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toVerify();
            }
        });

        return view;
    }
    public void toVerify(){
        name = textView_name.getText().toString();
        username = textView_username.getText().toString();
        password = textView_password.getText().toString();
        email = textView_email.getText().toString();
        phone = textView_phone.getText().toString();
        user = new User(name,username,password,phone,email);
        ApiService.apiService.getSignUpResult(user).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result res = response.body();
                String t = (res == null)?"0":res.getResult();
                Log.v("TAG",t);
                if (t.equals("Gmail existed")){
                      textView_email.setBackgroundResource(R.drawable.login_custom_textview_warning);
                      textView_email.setTextColor(getResources().getColor(R.color.red));
                      Toast.makeText(getContext(), t, Toast.LENGTH_SHORT).show();
                }
                else if (t.equals("Username existed")){
                      textView_username.setBackgroundResource(R.drawable.login_custom_textview_warning);
                      textView_username.setTextColor(getResources().getColor(R.color.red));
                      Toast.makeText(getContext(), t, Toast.LENGTH_SHORT).show();
                }
                else if (t.equals("0")){
                    textView_email.setBackgroundResource(R.drawable.login_custom_textview_warning);
                    textView_email.setTextColor(getResources().getColor(R.color.red));
                    Toast.makeText(getContext(), "Gmail not exist", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getActivity(),Verify.class);
                    intent.putExtra("User Information",user);
                    startActivity(intent);
                }


            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.v("TAG","Error in registering");
            }
        });
    }
}
