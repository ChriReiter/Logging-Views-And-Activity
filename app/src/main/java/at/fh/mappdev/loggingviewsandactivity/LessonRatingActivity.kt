package at.fh.mappdev.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.bumptech.glide.Glide

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
                    title = lesson.name
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
