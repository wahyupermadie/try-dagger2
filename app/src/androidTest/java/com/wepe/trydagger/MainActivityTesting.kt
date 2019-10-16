package com.wepe.trydagger

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.wepe.trydagger.ui.MainActivity
import com.wepe.trydagger.R.id.*
import com.wepe.trydagger.ui.movies.fragment.MoviesFragment
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
    }

    private fun delay(){
        try {
            Thread.sleep(10000)
        }catch (e : InterruptedException){
            e.printStackTrace()
        }
    }
}