package algonquin.cst2335.Matt041049353;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * standard main activity test example to demonstrate a password with missing characters
     */
    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatEditText = onView( withId(R.id.editText));

        appCompatEditText.perform(replaceText("12345"), closeSoftKeyboard());

        ViewInteraction materialButton = onView( withId(R.id.button1));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     * test case for missing uppercase letter(s)
     */
    @Test
    public void testMissingUpperCase(){
        ViewInteraction appCompatEditText = onView( withId(R.id.editText));
        appCompatEditText.perform(replaceText("password123#$"), closeSoftKeyboard());

        ViewInteraction materialButton = onView( withId(R.id.button1));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     * test case for missing special character
     */
    @Test
    public void testMissingSpecial(){
        ViewInteraction appCompatEditText = onView( withId(R.id.editText));
        appCompatEditText.perform(replaceText("SuperPass123"), closeSoftKeyboard());

        ViewInteraction materialButton = onView( withId(R.id.button1));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     * test case for missing lowercase letters
     */
    @Test
    public void testMissingLowerCase(){
        ViewInteraction appCompatEditText = onView( withId(R.id.editText));
        appCompatEditText.perform(replaceText("PASSWORD123#$"), closeSoftKeyboard());

        ViewInteraction materialButton = onView( withId(R.id.button1));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     * test case for missing number
     */
    @Test
    public void testMissingNumber(){
        ViewInteraction appCompatEditText = onView( withId(R.id.editText));
        appCompatEditText.perform(replaceText("Password!@$"), closeSoftKeyboard());

        ViewInteraction materialButton = onView( withId(R.id.button1));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     * test case for password that meets all the given requirements
     */
    @Test
    public void testAllRequirements(){
        ViewInteraction appCompatEditText = onView( withId(R.id.editText));
        appCompatEditText.perform(replaceText("Password123$#@"), closeSoftKeyboard());

        ViewInteraction materialButton = onView( withId(R.id.button1));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("meets the requirements")));
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
