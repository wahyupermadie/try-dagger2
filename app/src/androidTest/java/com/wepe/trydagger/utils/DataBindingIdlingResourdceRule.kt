package com.wepe.trydagger.utils

import androidx.test.espresso.IdlingRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class DataBindingIdlingResourceRule(
    activityTestRule: ActivityTestRule<*>
) : TestWatcher() {
    private val idlingResource = DataBindingIdlingResource(activityTestRule)

    override fun finished(description: Description?) {
        IdlingRegistry.getInstance().unregister(idlingResource)
        super.finished(description)
    }

    override fun starting(description: Description?) {
        IdlingRegistry.getInstance().register(idlingResource)
        super.starting(description)
    }

}