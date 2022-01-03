package at.fh.mappdev.loggingviewsandactivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate

class SettingsActivity : AppCompatActivity() {

    companion object {
        val DARKMODE = "DARKMODE"
        val USERNAME = "USERNAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        val userTxt = findViewById<EditText>(R.id.usernameText)
        val switchState = findViewById<Switch>(R.id.switchDarkMode)

        val savedString = sharedPreferences.getString(USERNAME, null)
        userTxt.setText(savedString)

        val savedBoolean = sharedPreferences.getBoolean(DARKMODE, true)
        switchState.isChecked = savedBoolean

        val saveBtn = findViewById<Button>(R.id.btnSaveSettings)
        saveBtn.setOnClickListener {
            sharedPreferences.edit().putString(USERNAME, userTxt.text.toString()).apply()
            sharedPreferences.edit().putBoolean(DARKMODE, switchState.isChecked).apply()
            finish()
        }
    }
}