package at.fh.mappdev.loggingviewsandactivity

import androidx.core.content.MimeTypeFilter.matches
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.regex.Pattern.matches

class UserRatesALessonTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
    }
    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun clickingLessonsButton_shouldLaunchLessonListActivity() {

        Espresso.onView(ViewMatchers.withId(R.id.open_lessons)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(LessonListActivity::class.java.name))

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.lesson_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItem<LessonViewHolder>(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText("Lecture 0")
                    ), ViewActions.click()
                )
            )
        Intents.intended(IntentMatchers.hasComponent(LessonRatingActivity::class.java.name))

        Thread.sleep(2000)
        onView(
            withId(
                R.id.lesson_name
            )
        ).check(
            ViewAssertions.matches(
                withText("Lecture 0")
            )
        )
        onView(withId(R.id.rate_lesson)).perform(ViewActions.click())
    }

//    @Test
//    fun clickingAnItem_opensDetail() {
//        Espresso.onView(ViewMatchers.withId(R.id.open_lessons)).perform(ViewActions.click())
//        Thread.sleep(2000)
//        Espresso.onView(ViewMatchers.withId(R.id.lesson_recycler_view))
//            .perform(
//                RecyclerViewActions.actionOnItem<LessonViewHolder>(
//                    ViewMatchers.hasDescendant(
//                        ViewMatchers.withText("Lecture 0")
//                    ), ViewActions.click()
//                )
//            )
//
//        Intents.intended(IntentMatchers.hasComponent(LessonRatingActivity::class.java.name))
//    }
//
//    @Test
//    fun checkingIfCorrectLessonIsDisplayed() {
//        Espresso.onView(ViewMatchers.withId(R.id.open_lessons)).perform(ViewActions.click())
//        Espresso.onView(ViewMatchers.withId(R.id.lesson_recycler_view))
//            .perform(
//                RecyclerViewActions.actionOnItem<LessonViewHolder>(
//                    ViewMatchers.hasDescendant(
//                        ViewMatchers.withText("Lecture 0")
//                    ), ViewActions.click()
//                )
//            )
//        Intents.intended(IntentMatchers.hasComponent(LessonRatingActivity::class.java.name))
//
//        Thread.sleep(2000)
//        onView(
//            withId(
//                R.id.lesson_name
//            )
//        ).check(
//            ViewAssertions.matches(
//                withText("Lecture 0")
//            )
//        )
//        onView(withId(R.id.rate_lesson)).perform(ViewActions.click())
//    }

}