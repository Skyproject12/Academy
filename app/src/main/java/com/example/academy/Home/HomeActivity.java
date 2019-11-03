package com.example.academy.Home;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.academy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity  {


    // set select bottom navigation
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()){
            case R.id.action_home:
                return true;
            case R.id.action_bookmark:
                return true;
        }
        return false;
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView= findViewById(R.id.nav_view);
        // devinition bottomNavigation
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        ArrayList<String> list= new ArrayList<>();
//        int a=2;
//        list.forEach(n->{
//            if(a==2){
//
//            }
//        });
    }

//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.action_home:
//                return true;
//            case R.id.action_bookmark:
//                return true;
//        }
//
//        return false;
//
//    }

}
