package com.vicks.mvvmtest.core

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.activityScenarioRule
import com.vicks.mvvmtest.R
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()


    @Test
    fun testIsActivityInView() {
        onView(ViewMatchers.withId(R.id.activity_main)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.btn_products)).check(matches(isDisplayed()))
    }

    @Test
    fun testButtonClick() {
        onView(ViewMatchers.withId(R.id.btn_products)).perform(click())
        onView(ViewMatchers.withId(R.id.activity_product)).check(matches(isDisplayed()))
    }
}