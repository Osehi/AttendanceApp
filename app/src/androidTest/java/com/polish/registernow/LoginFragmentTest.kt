package com.polish.registernow

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.reflect.typeOf

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {

    @Test
    fun testEmailEditText(){
        val scenario = launchFragmentInContainer<LoginFragment>()
       onView(withId(R.id.activity_main_email_et)).perform()
        onView(withId(R.id.activity_main_email_et))

    }

}