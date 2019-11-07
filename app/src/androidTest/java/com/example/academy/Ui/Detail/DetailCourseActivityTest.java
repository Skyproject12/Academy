package com.example.academy.Ui.Detail;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.ViewAssertion;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.academy.Data.CourseEntity;
import com.example.academy.R;
import com.example.academy.utils.FakeDataDummy;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import com.example.academy.utils.RecyclerViewItemCountAssertion;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DetailCourseActivityTest {

    private CourseEntity courseEntity = FakeDataDummy.generateDummy().get(0);
    @Rule
    public ActivityTestRule<DetailCourseActivity> activityTestRule = new ActivityTestRule<DetailCourseActivity>(DetailCourseActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailCourseActivity.class);
            result.putExtra(DetailCourseActivity.EXTRA_COURSE, courseEntity.getCourseId());
            return result;

        }
    };

    @Test
    public void loadCourse() {
        // memberi expectation hasil dari check
        onView(withId(R.id.text_title)).check(matches( isDisplayed()));
        // melakukan penyocokan berdasarkan  courseEntity getTitle
        onView(withId(R.id.text_title)).check(matches(withText(courseEntity.getTitle())));
        onView(withId(R.id.text_date)).check(matches(isDisplayed()));
        onView(withId(R.id.text_date)).check(matches(withText(String.format("Deadline %s", courseEntity.getDeadline()))));

    }
    @Test
    public void loadModules(){
        // check size of arraylist with expectation is 7
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_module)).check(new RecyclerViewItemCountAssertion(7));
    }
}