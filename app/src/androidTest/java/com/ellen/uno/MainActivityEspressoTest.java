package com.ellen.uno;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void initTest() {
        onView(withId(R.id.deckCount)).check(matches(withText("65")));
        onView(withId(R.id.computerCount)).check(matches(withText("7")));
    }

    @Test
    public void pickButtonTest() {
        onView(withId(R.id.pick)).perform(click());
        onView(withId(R.id.deckCount)).check(matches(withText("64")));
    }

}
