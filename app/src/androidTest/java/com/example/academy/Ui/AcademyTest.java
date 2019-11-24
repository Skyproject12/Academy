package com.example.academy.Ui;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.example.academy.R;
import com.example.academy.Ui.Home.HomeActivity;
import com.example.academy.Utils.IddlingTesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class AcademyTest {

    @Rule
    public ActivityTestRule<HomeActivity> activityTestRule= new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void setUp(){
        IdlingRegistry.getInstance().register(IddlingTesting.getIddlingTesting());

    }

    @After
    public void tearDown(){
        IdlingRegistry.getInstance().unregister(IddlingTesting.getIddlingTesting());

    }

    @Test
    public void toDetailActivityTest(){
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.text_title)).check(matches(withText("Menjadi Android Develover Expert")));

    }
    @Test
    public void toReaderActivityTest(){
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.btn_start)).perform(click());
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_module)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.web_view)).check(matches(isDisplayed()));

    }

}
