package at.fh.mappdev.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RatingBar

class RatingActivity : AppCompatActivity() {
    private val rating = Rating("android_lesson_01")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)
    }

    fun saveRating(v: View) {
        rating.feedback = findViewById<EditText>(R.id.feedbackInput).text.toString()
        rating.rating = findViewById<RatingBar>(R.id.ratingBar).rating.toDouble()


    }
}