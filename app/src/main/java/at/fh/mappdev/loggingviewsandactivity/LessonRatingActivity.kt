package at.fh.mappdev.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class LessonRatingActivity : AppCompatActivity() {

    companion object {
        val EXTRA_ADDED_OR_EDITED_RESULT = "EXTRA_ADDED_OR_EDITED_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_rating)

        val lessonId = intent.getStringExtra(LessonListActivity.EXTRA_LESSON_ID)
        var lessonIdInt = 0

        if (lessonId == null) {
            Toast.makeText(this, "No lessonID", Toast.LENGTH_SHORT).show()
        } else {

            LessonRepository.lessonById(
                lessonId,
                success = {
                    val lesson = it
                    val lessonTxt = findViewById<TextView>(R.id.lesson_rating_header)
                    lessonTxt.text = lesson.name
                    lessonIdInt = lessonId.toInt()
                },
                error = {
                    Log.e("API Error", "Something went wrong")
                }
            )
//            val lesson_rating_header = findViewById<TextView>(R.id.lesson_rating_header)
//            val lessonName = LessonRepository.lessonById(lessonId)?.name
//            lesson_rating_header.text = lessonName
//
//            val rateBtn = findViewById<Button>(R.id.rate_lesson)
//
//            rateBtn.setOnClickListener {
//                val ratingValue = findViewById<RatingBar>(R.id.lesson_rating_bar).rating.toDouble()
//                val feedbackTxt = findViewById<EditText>(R.id.lesson_feedback).toString()
//
//                val rating = LessonRating(ratingValue, feedbackTxt)
//
//                LessonRepository.rateLesson(lessonId, rating)
//
//                setResult(Activity.RESULT_OK)
//                finish()
        }
        val rateBtn = findViewById<Button>(R.id.rate_lesson)

        rateBtn.setOnClickListener {
            val ratingValue = findViewById<RatingBar>(R.id.lesson_rating_bar).rating.toDouble()
            val feedbackTxt = findViewById<EditText>(R.id.lesson_feedback).toString()
            val rating = LessonRating(ratingValue, feedbackTxt)

            LessonRepository.rateLesson(
                lessonIdInt,
                rating,
                success = {
                    val lesson = it
                    val lessonTxt = findViewById<TextView>(R.id.lesson_rating_header)
                    lessonTxt.text = lesson.name
                },
                error = {
                    Log.e("API Error", "Something went wrong!")
                }
            )

            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_ADDED_OR_EDITED_RESULT, "Added")
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}