package com.example.scame.testingsamples

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.IdlingResource
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.example.scame.testingsamples.samples.*
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers.isEmptyString
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class HttpTest {

    // comment/uncomment test rules to switch between activities

    /*@get:Rule
    val enqueueActivityTestRule = ActivityTestRule<HttpEnqueueSample>(HttpEnqueueSample::class.java)*/

   /* @get:Rule
    val rxActivityTestRule = ActivityTestRule<HttpRxSample>(HttpRxSample::class.java)*/

    /*@get:Rule
    val syncActivityTestRule = ActivityTestRule<HttpSynchronousSample>(HttpSynchronousSample::class.java)*/

    /*@get:Rule
    val eventBusActivityTestRule = ActivityTestRule<HttpEventBusSample>(HttpEventBusSample::class.java)*/

    @get:Rule
    val serviceLongActionActivityTestRule = ActivityTestRule<ServiceLongActionSample>(ServiceLongActionSample::class.java)

    private lateinit var idlingResource: IdlingResource

    @Before
    fun setupIdlingResource() {
        // replace idlingResource source accordingly
        idlingResource = serviceLongActionActivityTestRule.activity.idlingResource
    }

    @Test
    fun testTextIsDisplayed() {
        onView(withId(R.id.button)).perform(click())
        IdlingRegistry.getInstance().register(idlingResource)
        onView(withId(R.id.textView)).check(matches(withText(not(isEmptyString()))))
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }
}