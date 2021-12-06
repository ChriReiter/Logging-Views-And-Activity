package at.fh.mappdev.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Moshi
import at.fh.mappdev.loggingviewsandactivity.LessonRatingActivity.Companion.EXTRA_ADDED_OR_EDITED_RESULT

class LessonListActivity : AppCompatActivity() {

    companion object {
        val EXTRA_LESSON_ID = "LESSON_ID_EXTRA"
        val ADD_OR_EDIT_RATING_REQUEST = 1
    }

    val lessonAdapter = LessonAdapter() { lesson ->
        val implicitIntent = Intent(this, LessonRatingActivity::class.java)
        implicitIntent.putExtra(EXTRA_LESSON_ID, lesson.id)
        startActivityForResult(implicitIntent, ADD_OR_EDIT_RATING_REQUEST)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)

        //lessonAdapter.updateList(LessonRepository.lessonsList())

        LessonRepository.lessonsList(
            success = {
                lessonAdapter.updateList(it)
            },
            error = {
                Log.e("API Error", "Something went wrong!")
            }
        )

        val recyclerView = findViewById<RecyclerView>(R.id.lesson_recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = lessonAdapter

        parseJson()

        SleepyAsyncTask().execute()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == ADD_OR_EDIT_RATING_REQUEST) {
            val resultExtra = data?.getStringExtra(EXTRA_ADDED_OR_EDITED_RESULT) ?: return

            LessonRepository.lessonsList(
                success = {
                    lessonAdapter.updateList(it)
                },
                error = {
                    Log.e("API Error", "Something went wrong!")
                }
            )
        }
    }

    fun parseJson() {
        val json = """
            {
                "id": "1",
                "name": "Lecture 0",
                "date": "09.10.2019",
                "topic": "Introduction",
                "type": "LECTURE",
                "lecturers": [
                    {
                        "name": "Lukas Bloder"
                    },
                    {
                        "name": "Peter Salhofer"
                    }
                ],
                "ratings": []
            }
        """
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter<Lesson>(Lesson::class.java)
        val result = jsonAdapter.fromJson(json)
        if (result != null) {
            Log.i("Moshi ", result.name)
        }
    }
}