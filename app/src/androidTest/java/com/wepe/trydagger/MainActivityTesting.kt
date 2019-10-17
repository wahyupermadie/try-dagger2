package com.wepe.trydagger

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.wepe.trydagger.R.id.*
import com.wepe.trydagger.ui.MainActivity
import com.wepe.trydagger.utils.DataBindingIdlingResourceRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTesting {
    @get:Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule(activityRule)

    @Before
    fun setUp(){
        activityRule.launchActivity(Intent())
    }

    @Test
    fun mainTesting(){
        delay()

        onView(withId(rv_movies)).check(matches(isDisplayed()))
        onView(withId(rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))

        onView(withId(tv_menu)).check(matches(isDisplayed()))
        onView(withId(tv_menu)).perform(ViewActions.click())

        onView(withId(rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))

    }

    @Test
    fun detailActivityTesting(){
        delay()


        onView(withId(movies_menu)).perform(ViewActions.click())
        onView(withId(rv_movies)).check(matches(isDisplayed()))
        onView(withId(rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, ViewActions.click()))

        onView(withId(imageView)).check(matches(isDisplayed()))
        onView(withId(textView)).check(matches(isDisplayed()))
        onView(withId(textView2)).check(matches(isDisplayed()))
        onView(withId(textView3)).check(matches(isDisplayed()))
        onView(withId(textView4)).check(matches(isDisplayed()))
        onView(withId(textView5)).check(matches(isDisplayed()))
        onView(withId(textView6)).check(matches(isDisplayed()))

        onView(isRoot()).perform(ViewActions.pressBack())

        onView(withId(tv_menu)).perform(ViewActions.click())
        onView(withId(rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, ViewActions.click()))

        onView(withId(imageView)).check(matches(isDisplayed()))
        onView(withId(textView)).check(matches(isDisplayed()))
        onView(withId(textView2)).check(matches(isDisplayed()))
        onView(withId(textView3)).check(matches(isDisplayed()))
        onView(withId(textView4)).check(matches(isDisplayed()))
        onView(withId(textView5)).check(matches(isDisplayed()))
        onView(withId(textView6)).check(matches(isDisplayed()))
    }

    private fun delay(){
        try {
            Thread.sleep(5000)
        }catch (e : InterruptedException){
            e.printStackTrace()
        }
    }
}