package at.fh.mappdev.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.bumptech.glide.Glide
import at.fh.mappdev.loggingviewsandactivity.LessonListActivity.Companion.EXTRA_LESSON_ID

class LessonRatingActivity : AppCompatActivity() {

    companion object {
        val EXTRA_ADDED_OR_EDITED_RESULT = "EXTRA_ADDED_OR_EDITED_RESULT"
        val EXTRA_LESSON_NAME = "EXTRA_LESSON_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_rating)

        val lessonId = intent.getStringExtra(LessonListActivity.EXTRA_LESSON_ID)
        var lessonIdInt = 0
        var lessonName = ""

        if (lessonId == null) {
            Toast.makeText(this, "No lessonID", Toast.LENGTH_SHORT).show()
        } else {

            LessonRepository.lessonById(
                lessonId,
                success = {
                    val lesson = it
                    title = lesson.name
                    lessonName = lesson.name
                    lessonIdInt = lessonId.toInt()

                    val imageView = findViewById<ImageView>(R.id.lesson_image)
                    Glide.with(this).load(lesson.imageUrl).into(imageView)

                    findViewById<TextView>(R.id.lesson_name).text = title
                    findViewById<RatingBar>(R.id.lesson_avg_ratingBar).rating = it.ratingAverage().toFloat()
                    val rating = it.ratingAverage().format(2)
                    findViewById<TextView>(R.id.lesson_avg_ratingText).text = rating

                    val feedback = firstEntry( it.ratings )
                    findViewById<TextView>(R.id.lesson_ViewFeedback).text = feedback
                },
                error = {
                    Log.e("API Error", "Something went wrong")
                }
            )
        }

        val noteBtn = findViewById<Button>(R.id.btnOpenNotes)
        noteBtn.setOnClickListener {
            val noteIntent = Intent(this, LessonNoteActivity::class.java)
            noteIntent.putExtra(EXTRA_LESSON_ID, lessonId)
            noteIntent.putExtra(EXTRA_LESSON_NAME, lessonName)
            startActivityForResult(noteIntent, LessonNoteActivity.ADD_NOTE_REQUEST)
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

        val addNoteBtn = findViewById<Button>(R.id.btnOpenNotes)
        addNoteBtn.setOnClickListener {
            val intent = Intent(this, LessonNoteActivity::class.java)
            startActivity(intent)
        }
    }
    private fun Double.format(digits: Int) = "%.${digits}f".format(this)

    private fun firstEntry(list: List<LessonRating>, i:Int = 0):String {
        if (list.size <= i)
            return ""
        else if(list[i].feedback != "")
            return list[i].feedback

        return firstEntry(list, i+1)
    }
}
