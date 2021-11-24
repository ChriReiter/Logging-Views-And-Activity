package at.fh.mappdev.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar

class RatingActivity : AppCompatActivity() {
    private val rating = Rating("android_lesson_01")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)
    }

    fun saveRating(v: View) {
        rating.feedback = findViewById<EditText>(R.id.feedback_input).text.toString()
        rating.rating = findViewById<RatingBar>(R.id.rating_bar).rating.toDouble()


    }
}