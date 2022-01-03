package at.fh.mappdev.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import at.fh.mappdev.loggingviewsandactivity.LessonListActivity.Companion.EXTRA_LESSON_ID
import at.fh.mappdev.loggingviewsandactivity.LessonRatingActivity.Companion.EXTRA_LESSON_NAME

class LessonNoteActivity : AppCompatActivity() {

    companion object {
        val ADD_NOTE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_note)

        val lessonName = intent.getStringExtra(EXTRA_LESSON_NAME)
        val lessonId = intent.getStringExtra(EXTRA_LESSON_ID).toString()
        val editText = findViewById<EditText>(R.id.noteText)

        findViewById<TextView>(R.id.lessonName).text = lessonName

        val note = LessonRepository.findLessonNoteById(this, lessonId)

        if (!note.text.isNullOrEmpty()) {
                editText.setText(note.text)
                Log.e("Note", note.text)
            }

        val saveBtn = findViewById<Button>(R.id.btnSaveNote)
        saveBtn.setOnClickListener {
            val lessonNote = LessonNote(
                lessonId,
                lessonName.toString(),
                editText.text.toString()
            )
            LessonRepository.addLessonNote(this, lessonNote)
            val note = LessonRepository.findLessonNoteById(this, lessonId)
            editText.setText(note.text)
        }
    }
}