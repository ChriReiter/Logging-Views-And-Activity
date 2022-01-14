package at.fh.mappdev.loggingviewsandactivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import at.fh.mappdev.loggingviewsandactivity.SettingsActivity.Companion.DARKMODE
import at.fh.mappdev.loggingviewsandactivity.SettingsActivity.Companion.USERNAME

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MyActivity", "onCreate")

        findViewById<Button>(R.id.open_views).setOnClickListener {
            val intent = Intent(this, ViewsActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.share_main).setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        findViewById<Button>(R.id.open_rating).setOnClickListener {
            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.open_lessons).setOnClickListener {
            val intent = Intent(this, LessonListActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.open_settings).setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.open_viewmodel).setOnClickListener {
            val intent = Intent(this, ViewModelActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        Log.i("MyActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()

        val savedUser = findViewById<TextView>(R.id.userTxt)
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString(USERNAME, null)
        savedUser.text = savedString

        val savedSwitch = sharedPreferences.getBoolean(DARKMODE, true)
        if (savedSwitch)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        Log.i("MyActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MyActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MyActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MyActivity", "onDestroy")
    }
}