package at.fh.mappdev.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class LessonRatingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_rating)

        val lessonId = intent.getStringExtra(LessonListActivity.EXTRA_LESSON_ID)

        if (lessonId == null) {
            Toast.makeText(this, "No lessonID", Toast.LENGTH_SHORT).show()
        } else {
            val lesson_rating_header = findViewById<TextView>(R.id.lesson_rating_header)
            val lessonName = LessonRepository.lessonById(lessonId)?.name
            lesson_rating_header.text = lessonName
        }
    }
}