package at.fh.mappdev.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import at.fh.mappdev.loggingviewsandactivity.LessonListActivity.Companion.EXTRA_LESSON_ID
import at.fh.mappdev.loggingviewsandactivity.LessonRatingActivity.Companion.EXTRA_LESSON_NAME

class LessonNoteActivity : AppCompatActivity() {

    companion object {
        val ADD_NOTE_REQUEST = 1
    }
    private val viewModel: LessonNoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_note)

        val lessonName = intent.getStringExtra(EXTRA_LESSON_NAME)
        val lessonId = intent.getStringExtra(EXTRA_LESSON_ID).toString()
        val editText = findViewById<EditText>(R.id.noteText)
        val lessonNoteTextView = findViewById<TextView>(R.id.lesson_note_text_view)

        findViewById<TextView>(R.id.lessonName).text = lessonName

        viewModel.findLessonNoteById(lessonId)
        viewModel.note.observe(this) {
            if (it != null) {
                if(!it.text.isNullOrEmpty())
                    lessonNoteTextView.text = it?.text
            }
        }

//        val note = LessonRepository.findLessonNoteById(this, lessonId)
//
//        if (!note.text.isNullOrEmpty()) {
//                lessonNoteTextView.text = note?.text
//                Log.e("Note", note.text)
//            }

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