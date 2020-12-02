package com.jingtian.springrain

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.jingtian.springrain.splash.SplashActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SplashActivityTest {

    @get:Rule
    var intentsRule: IntentsTestRule<SplashActivity> = IntentsTestRule(SplashActivity::class.java)

    @Test
    fun testAidlBtn_click_shouldShowName() {
        onView(withId(R.id.aidlBtn)).perform(click())
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        onView(withId(R.id.tv_aidl))
            .check(matches(withText(appContext.resources.getString(R.string.aidl_service_name))))
    }

    @Test
    fun testNextBtn_click_ShouldStartMainActivity() {
        onView(withId(R.id.nextBtn)).perform(click())
        intended(
            allOf(
                hasComponent(hasShortClassName(".MainActivity")),
                toPackage("com.jingtian.springrain")
            )
        )
    }
}
