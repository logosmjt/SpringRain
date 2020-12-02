package com.jingtian.springrain

import android.content.Intent
import android.widget.Button
import com.jingtian.springrain.splash.SplashActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows

@RunWith(RobolectricTestRunner::class)
class SplashActivityRobolectricTest {
    lateinit var splashActivity: SplashActivity

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        splashActivity = Robolectric.buildActivity(SplashActivity::class.java).create().get()
    }

    @Test
    fun testNextBtn_click_ShouldStartMainActivity() {
        splashActivity.findViewById<Button>(R.id.nextBtn).performClick()
        val intent = Intent(splashActivity, MainActivity::class.java)
        Assert.assertEquals(
            intent.component,
            Shadows.shadowOf(splashActivity).nextStartedActivity.component
        )
    }
}
