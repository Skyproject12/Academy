package com.example.academy.utils;

import android.view.View;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
public class RecyclerViewItemCountAssertion implements ViewAssertion {

    private final int expectedCount;

    public RecyclerViewItemCountAssertion(int expectedCount) {
        this.expectedCount = expectedCount;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        // berfungsi untuk melakukan pengecekan pada recyclerview
        if(noViewFoundException!=null){
            throw  noViewFoundException;
        }
        RecyclerView recyclerView= (RecyclerView) view;
        RecyclerView.Adapter adapter= recyclerView.getAdapter();
        //assertNotNull(adapter);
        assertThat(adapter.getItemCount(), is(expectedCount));

    }
}
