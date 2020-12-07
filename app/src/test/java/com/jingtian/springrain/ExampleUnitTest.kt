package com.jingtian.springrain

import android.content.Context
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @Mock
    lateinit var mockContext: Context

    @Test
    fun addition_isCorrect() {
        assertEquals(5, 2 + 2)
    }

    @Test
    fun appName_isCorrect() {
        `when`(mockContext.getString(R.string.app_name)).thenReturn(APP_NAME_STRING)
        val result: String = mockContext.getString(R.string.app_name)
        assertThat(result, `is`(APP_NAME_STRING))
    }

    companion object {
        private const val APP_NAME_STRING = "SpringRain"
    }
}
