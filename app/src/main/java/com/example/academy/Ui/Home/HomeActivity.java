package com.example.academy.Ui.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.academy.R;
import com.example.academy.Ui.Academy.AcademyFragment;
import com.example.academy.Ui.BookMark.BookmarkFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity  {

    private final String SELECTED_MENU= "selected_menu";
    private BottomNavigationView navView;


    // set select bottom navigation
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        Fragment fragment=null;
        if(item.getItemId()==R.id.action_home) {
           fragment = AcademyFragment.newInstance();
        }
        else if(item.getItemId()==R.id.action_bookmark) {
           fragment = BookmarkFragment.newInstance();
        }
        if(fragment!=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.container, fragment)
                    .commit();
        }
        return true;
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navView= findViewById(R.id.nav_view);
        // devinition bottomNavigation
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // jka tidak null
        if(savedInstanceState!=null){
            // set selected berdasarkan id yang dikirim dari saveInstance
            savedInstanceState.getInt(SELECTED_MENU);
        }
        else{
            // set selected defauld action home
            navView.setSelectedItemId(R.id.action_home);
        }
//        ArrayList<String> list= new ArrayList<>();
//        int a=2;
//        list.forEach(n->{
//            if(a==2){
//
//            }
//        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // set fragment sesuai dengan selectedId
        outState.putInt(SELECTED_MENU, navView.getSelectedItemId());
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
