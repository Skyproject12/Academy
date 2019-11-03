package com.example.academy.Home;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.example.academy.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ArrayList<String> list= new ArrayList<>();
//        int a=2;
//        list.forEach(n->{
//            if(a==2){
//
//            }
//        });
    }
}
