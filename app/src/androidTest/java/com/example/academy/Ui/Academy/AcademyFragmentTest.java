package com.example.academy.Ui.Academy;

import androidx.test.rule.ActivityTestRule;

import com.example.academy.R;
import com.example.academy.testing.SingleFragmentActivity;
import com.example.academy.utils.RecyclerViewItemCountAssertion;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

public class AcademyFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityTestRule= new ActivityTestRule<>(SingleFragmentActivity.class);
    private AcademyFragment academyFragment= new AcademyFragment();
    @Before
    public void setUp(){
        // set fragment to until tes
        activityTestRule.getActivity().setFragment(academyFragment);
    }
    @Test
    public void loadCourses(){
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()));
        // membandingkan jumlah item dalam recyclerview
        onView(withId(R.id.rv_academy)).check(new RecyclerViewItemCountAssertion(5));
    }

}