package com.example.academy.Ui.Reader;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.R;
import com.example.academy.utils.FakeDataDummy;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import com.example.academy.utils.RecyclerViewItemCountAssertion;

public class CourseReaderActivityTest {

    private CourseEntity courseEntity = FakeDataDummy.generateDummy().get(0);
    @Rule
    public ActivityTestRule<CourseReaderActivity> activityTestRule = new ActivityTestRule<CourseReaderActivity>(CourseReaderActivity.class) {


        // intent to CourseReaderActivity from targetActivity
        @Override
        protected Intent getActivityIntent() {

            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, CourseReaderActivity.class);
            result.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseEntity.getCourseId());
            return result;

        }
    };

    @Test
    public void loadModule() {
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e){
            e.printStackTrace();

        }
        // get size of recyclerview
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()));
        // memberi expectation intos checking
        onView(withId(R.id.rv_module)).check(new RecyclerViewItemCountAssertion(7));
    }

    @Test
    public void clickModule() {
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e){
            e.printStackTrace();

        }
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()));
        // check ketika recyclerview diklik
        onView(withId(R.id.rv_module)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.web_view)).check(matches(isDisplayed()));

    }

}