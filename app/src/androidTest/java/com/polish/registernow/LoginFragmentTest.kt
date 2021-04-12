package com.polish.registernow

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.reflect.typeOf

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {

//    @Test
//    fun testEmailEditText(){
//        val scenario = launchFragmentInContainer<LoginFragment>()
//       onView(withId(R.id.activity_main_email_et)).perform()
//
//    }

    @Test
    fun test_isLoginFragmentScreen_Visible(){
        val scenario = launchFragmentInContainer<LoginFragment>(
           themeResId = R.style.Theme_RegisterNow
        )

        scenario.moveToState(Lifecycle.State.STARTED)
        onView(withId(R.id.fragment_login_lo)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isLoginButton_Displayed(){
        // the scenario helps to launch the fragment
        val scenario = launchFragmentInContainer<LoginFragment>(
                themeResId = R.style.Theme_RegisterNow
        )
        // at the "START STATE" of the LifeCycle when I set it with this code
        scenario.moveToState(Lifecycle.State.STARTED)
        // check if this login button is visible
        onView(withId(R.id.fragment_login_login_btn)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isLoginButton_isClickable(){
        val scenario = launchFragmentInContainer<LoginFragment>(
                themeResId = R.style.Theme_RegisterNow
        )

        scenario.moveToState(Lifecycle.State.STARTED)
        onView(withId(R.id.fragment_login_login_btn)).check(matches(isClickable()))

    }

    /**
     * the purpose of this test is to type into the email view
     */
    @Test
    fun test_emailView_receivesEmailText(){
        val scenario = launchFragmentInContainer<LoginFragment>(
                themeResId = R.style.Theme_RegisterNow
        )

        scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.activity_main_email_et)).perform(typeText("osehiaseehilen@gmail.com"))
        onView(withId(R.id.activity_main_email_et)).check(matches(withText("osehiaseehilen@gmail.com")))
    }

    @Test
    fun test_passwordView_receivesPasswordTexted(){
        val scenario = launchFragmentInContainer<LoginFragment>(
                themeResId = R.style.Theme_RegisterNow
        )

        scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.fragment_login_password_et)).perform(typeText("Connected1"))
        onView(withId(R.id.fragment_login_password_et)).check(matches(withText("Connected1")))
    }

}