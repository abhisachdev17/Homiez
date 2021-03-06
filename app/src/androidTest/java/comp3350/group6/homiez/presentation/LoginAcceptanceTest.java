package comp3350.group6.homiez.presentation;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.group6.homiez.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginAcceptanceTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void validLoginTest() {
        // Enter 0 as userid
        ViewInteraction editText = onView(allOf(withId(R.id.editUserID), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed()));
        editText.perform(replaceText("0"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(allOf(withId(R.id.editUserID), withText("0"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed()));
        editText3.perform(pressImeActionButton());

        // Enter dev as password
        ViewInteraction editText4 = onView(allOf(withId(R.id.editPassword), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 2), isDisplayed()));
        editText4.perform(replaceText("dev"), closeSoftKeyboard());

        // Click login button
        ViewInteraction button = onView(allOf(withId(R.id.loginButton), withText("Login"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3), isDisplayed()));
        button.perform(click());

        // Check if brought passed login to next page
        ViewInteraction postinglist = onView(allOf(withId(R.id.postingsList), isDisplayed()));
        postinglist.check(matches(isDisplayed()));
    }

    @Test
    public void invalidPasswordLoginTest() {
        // Enter 0 as userid
        ViewInteraction editText = onView(allOf(withId(R.id.editUserID), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed()));
        editText.perform(replaceText("0"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(allOf(withId(R.id.editUserID), withText("0"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed()));
        editText3.perform(pressImeActionButton());

        //Enter invalid password
        ViewInteraction editText4 = onView(allOf(withId(R.id.editPassword), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 2), isDisplayed()));
        editText4.perform(replaceText("INVALID_PASSWORD"), closeSoftKeyboard());

        // Click login
        ViewInteraction button = onView(allOf(withId(R.id.loginButton), withText("Login"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3), isDisplayed()));
        button.perform(click());

        // Check that it doesn't login you in
        ViewInteraction textView = onView(
                allOf(withId(android.R.id.message), withText("Incorrect username or password"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Incorrect username or password")));
    }

    @Test
    public void invalidIdLoginTest() {
        // Enter invalid id
        ViewInteraction editText = onView(allOf(withId(R.id.editUserID), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed()));
        editText.perform(replaceText("INVALID_USER_ID"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(allOf(withId(R.id.editUserID), withText("INVALID_USER_ID"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed()));
        editText3.perform(pressImeActionButton());

        // Enter dev as password
        ViewInteraction editText4 = onView(allOf(withId(R.id.editPassword), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 2), isDisplayed()));
        editText4.perform(replaceText("dev"), closeSoftKeyboard());

        // Click login button
        ViewInteraction button = onView(allOf(withId(R.id.loginButton), withText("Login"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3), isDisplayed()));
        button.perform(click());

        // Check that it doesn't login you in
        ViewInteraction textView = onView(
                allOf(withId(android.R.id.message), withText("Incorrect username or password"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Incorrect username or password")));
    }

    @Test
    public void noInputLoginTest() {
        // Click the login button
        ViewInteraction button = onView(allOf(withId(R.id.loginButton), withText("Login"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3), isDisplayed()));
        button.perform(click());

        // Check that it doesn't login you in
        ViewInteraction textView = onView(
                allOf(withId(android.R.id.message), withText("Incorrect username or password"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Incorrect username or password")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
