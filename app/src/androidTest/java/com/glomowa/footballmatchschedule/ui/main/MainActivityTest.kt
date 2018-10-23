package com.glomowa.footballmatchschedule.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.glomowa.footballmatchschedule.R.id.add_to_favorite
import com.glomowa.footballmatchschedule.R.id.drawer_layout
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppBehavior() {
        Thread.sleep(5000)
        onView(withText("Liverpool")).perform(click())

        Thread.sleep(3000)
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added to Favorite"))
                .check(matches(isDisplayed()))
        pressBack()


        onView(withId(drawer_layout)).perform(DrawerActions.open())
        onView(withText("Favorite Match")).perform(click())

        Thread.sleep(3000)
        onView(withText("Liverpool")).perform(click())
    }
}