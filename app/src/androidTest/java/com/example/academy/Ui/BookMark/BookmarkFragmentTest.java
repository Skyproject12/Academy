package com.example.academy.Ui.BookMark;

import androidx.test.rule.ActivityTestRule;

import com.example.academy.R;
import com.example.academy.testing.SingleFragmentActivity;
import com.example.academy.utils.RecyclerViewItemCountAssertion;

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
        activityTestRule.getActivity().setFragment(bookmarkFragment);
    }
    @Test
    public void loadBookmarrk() {
        try {
            Thread.sleep(3000);
        }
        catch(InterruptedException e){
            e.printStackTrace();

        }
        onView(withId(R.id.rv_bookmark)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_bookmark)).check(new RecyclerViewItemCountAssertion(5));
    }

}