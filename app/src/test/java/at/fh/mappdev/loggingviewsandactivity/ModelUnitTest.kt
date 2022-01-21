package at.fh.mappdev.loggingviewsandactivity

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test

class ModelUnitTest {

    @Test
    fun averageForEmptyRates_isCorrect() {

        // test whether the average is 0.0 when ratings are empty
        var lesson = Lesson("0",
            "Lecture 0",
            "01.10.2020",
            "Introduction",
            LessonType.LECTURE,
            listOf(),
            mutableListOf()
        )

        val average = lesson.ratingAverage()
        assertThat(average).isWithin(0.0).of(0.0)
    }

    @Test
    fun averageForNonEmptyRates_isCorrect() {

        // check whether the average is correct for a non-empty list of ratings
        var lesson = Lesson("0",
            "Lecture 0",
            "01.10.2020",
            "Introduction",
            LessonType.LECTURE,
            listOf(),
            mutableListOf()
        )

        val average = lesson.ratingAverage()
        assertThat(average).isWithin(2.5).of(2.5)
    }
}