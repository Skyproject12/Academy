package com.example.academy.Ui.BookMark;

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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class BookmarkFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityTestRule= new ActivityTestRule<>(SingleFragmentActivity.class);
    private BookmarkFragment bookmarkFragment= new BookmarkFragment();
    @Before
    public void setUp(){
        IdlingRegistry.getInstance().register(IddlingTesting.getIddlingTesting());

        activityTestRule.getActivity().setFragment(bookmarkFragment);
    }

    @After
    public void tearDown(){
        IdlingRegistry.getInstance().unregister(IddlingTesting.getIddlingTesting());

    }

    @Test
    public void loadBookmarrk() {
        onView(withId(R.id.rv_bookmark)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_bookmark)).check(new RecyclerViewItemCountAssertion(5));
    }

}