package com.example.academy.Ui.Academy;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.example.academy.R;
import com.example.academy.Utils.IddlingTesting;
import com.example.academy.testing.SingleFragmentActivity;

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

public class AcademyFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityTestRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private AcademyFragment academyFragment = new AcademyFragment();

    @Before
    public void setUp() {
        // set iddling Testing
        IdlingRegistry.getInstance().register(IddlingTesting.getIddlingTesting());

        // set fragment to until tes
        activityTestRule.getActivity().setFragment(academyFragment);
    }

    @After
    public void tearDown() {
        // unregis iddling testing
        IdlingRegistry.getInstance().unregister(IddlingTesting.getIddlingTesting());

    }

    @Test
    public void toDetailActivityTest() {
        // melakukan pengecekan apakah judul dari course sesuai
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));


        onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_title)).check(matches(withText("Menjadi Android Developer Expert")));

    }

    @Test
    public void toReaderActivityTest() {
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.btn_start)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_start)).perform(click());
        // melakukan pengcekan apakah memiliki ukuran 600 atau tidak
        try {
            onView(withId(R.id.frame_container)).check(matches(isDisplayed()));
            onView(withId(R.id.rv_module)).check(matches(isDisplayed()));
            onView(withId(R.id.rv_module)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
            onView(withId(R.id.web_view)).check(matches(isDisplayed()));

        } catch (NoMatchingViewException e) {
            onView(withId(R.id.frame_list)).check(matches(isDisplayed()));
            onView(withId(R.id.rv_module)).check(matches(isDisplayed()));
            onView(withId(R.id.rv_module)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
            onView(withId(R.id.web_view)).check(matches(isDisplayed()));
            onView(withId(R.id.web_view)).check(matches(isDisplayed()));

        }
    }

}