package com.example.academy.testing;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.academy.R;

public class SingleFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View content= new FrameLayout(this);
        // set param default
        FrameLayout.LayoutParams params= new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        content.setLayoutParams(params);
        content.setId(R.id.container);
        setContentView(content);
    }
    public void setFragment(Fragment fragment){
        // set fragment
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
    public void replaceFragment(Fragment fragment){
        // replace fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment)
                .commit();
    }
}
