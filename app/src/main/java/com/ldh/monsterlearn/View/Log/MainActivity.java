package com.ldh.monsterlearn.View.Log;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.ldh.monsterlearn.R;

public class MainActivity extends AppCompatActivity {
   FrameLayout frameLayout;
   Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        frameLayout = findViewById(R.id.login_framelayout);
        login = findViewById(R.id.login_button_login);
        register = findViewById(R.id.login_button_register);

    }
    public void changeFragment(View view){
       FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.login_button_login:
               login_fragment_login fragment1 = new login_fragment_login();
                fragmentTransaction.replace(R.id.login_framelayout,fragment1);
                fragmentTransaction.commit();
                break;
            case R.id.login_button_register:
                login_fragment_register fragment2 = new login_fragment_register();
                fragmentTransaction.replace(R.id.login_framelayout,fragment2);
                fragmentTransaction.commit();
                break;

        }
    }
}