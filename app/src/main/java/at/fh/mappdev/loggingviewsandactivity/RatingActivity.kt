package at.fh.mappdev.loggingviewsandactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView

class RatingActivity : AppCompatActivity() {

    private val rating = Rating("android_lesson_01")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)
    }

    fun saveRating(v: View) {
        rating.feedback = findViewById<EditText>(R.id.feedbackInput).text.toString()
        rating.rating = findViewById<RatingBar>(R.id.ratingBar).rating.toDouble()

        val feedbackTxt:TextView = findViewById(R.id.feedback)
        val ratingTxt:TextView = findViewById(R.id.rating_value)
        val lessonTxt:TextView = findViewById(R.id.lesson)

        feedbackTxt.text = rating.feedback.toString()
        ratingTxt.text = rating.rating.toString()
        lessonTxt.text = rating.lesson
    }
}