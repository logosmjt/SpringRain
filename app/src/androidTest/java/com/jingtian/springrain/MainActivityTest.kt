package com.jingtian.springrain

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.jingtian.springrain.ui.home.OperationAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @get:Rule
    var intentsRule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)

    @Test
    fun testHomeFrag_clickRecyclerView(){
        val position = 1
        onView(withId(R.id.navigation_home)).perform(click())

        onView(withId(R.id.operations)).perform(
            actionOnItemAtPosition<OperationAdapter.BannerViewHolder>(position,click()));
    }
}