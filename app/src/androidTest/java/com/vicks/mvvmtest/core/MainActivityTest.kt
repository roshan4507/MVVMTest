package com.vicks.mvvmtest.core

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.activityScenarioRule
import com.vicks.mvvmtest.R
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityScenarioRule  = activityScenarioRule<MainActivity>()

    @Test
    fun testGetProductsButton(){
     Espresso.onView(ViewMatchers.withId(R.id.btn_products)).perform(click())
    }
}