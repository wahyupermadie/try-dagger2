package com.wepe.trydagger

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.wepe.trydagger.R.id.*
import com.wepe.trydagger.ui.MainActivity
import com.wepe.trydagger.utils.DataBindingIdlingResourceRule
import com.wepe.trydagger.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule(activityRule)

    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
    }

    @Test
    fun mainTesting(){

        onView(withId(rv_movies)).check(matches(isDisplayed()))
        onView(withId(rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))

        onView(withId(tv_menu)).check(matches(isDisplayed()))
        onView(withId(tv_menu)).perform(ViewActions.click())

        onView(withId(rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))

    }

    @Test
    fun detailActivityTesting(){

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

}