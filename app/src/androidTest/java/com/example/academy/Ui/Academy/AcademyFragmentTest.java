package com.example.academy.Ui.Academy;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.academy.R;
import com.example.academy.Utils.IddlingTesting;
import com.example.academy.testing.SingleFragmentActivity;
import com.example.academy.utils.RecyclerViewItemCountAssertion;

import org.junit.After;
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
        // set iddling Testing
        IdlingRegistry.getInstance().register(IddlingTesting.getIddlingTesting());

        // set fragment to until tes
        activityTestRule.getActivity().setFragment(academyFragment);
    }

    @After
    public void tearDown(){
        // unregis iddling testing
        IdlingRegistry.getInstance().unregister(IddlingTesting.getIddlingTesting());

    }

    @Test
    public void loadCourses(){
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()));
        // membandingkan jumlah item dalam recyclerview
        onView(withId(R.id.rv_academy)).check(new RecyclerViewItemCountAssertion(5));
    }

}