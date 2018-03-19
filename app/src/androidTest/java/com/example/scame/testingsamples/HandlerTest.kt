package com.example.scame.testingsamples

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.scame.testingsamples.samples.HandlerSample
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HandlerTest {

    @get:Rule
    val activityTestRule = ActivityTestRule<HandlerSample>(HandlerSample::class.java)


    @Test
    fun testImgIsVisible() {
        onView(withId(R.id.button)).perform(click())
        IdlingRegistry.getInstance().register(activityTestRule.activity.idlingResource)
        onView(withId(R.id.img)).check(matches(isDisplayed()))
    }

    @Test
    fun testProgressIndicator() {
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
        IdlingRegistry.getInstance().register(activityTestRule.activity.idlingResource)
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(activityTestRule.activity.idlingResource)
    }
}