package com.example.academy.Ui;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.example.academy.R;
import com.example.academy.Ui.Home.HomeActivity;

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
    @Test
    public void toDetailActivityTest(){
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e){
            e.printStackTrace();

        }
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e){
            e.printStackTrace();

        }
        onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_title)).check(matches(withText("Menjadi Android Develover Expert")));

    }
    @Test
    public void toReaderActivityTest(){
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e){
            e.printStackTrace();

        }
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e){
            e.printStackTrace();

        }
        onView(withId(R.id.btn_start)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_start)).perform(click());
        onView(withId(R.id.frame_container)).check(matches(isDisplayed()));
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e){
            e.printStackTrace();

        }
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_module)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e){
            e.printStackTrace();

        }
        onView(withId(R.id.web_view)).check(matches(isDisplayed()));

    }

}
