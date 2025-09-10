package com.example.kardashin_b2_v3

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button;
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import java.util.*

class LoginActivity : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var password: EditText
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        email = findViewById(R.id.email_text)
        password = findViewById(R.id.password_text)

        var hour = findViewById<TextView>(R.id.hour)
        var minute = findViewById<TextView>(R.id.minute)

        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        hour.text = currentHour.toString()
        minute.text = currentMinute.toString()

        sharedPreferences = getSharedPreferences("app_data", Context.MODE_PRIVATE)

        val email_ = sharedPreferences.getString("email", "")
        email.setText(email_)
        val pass = sharedPreferences.getString("password", "")
        password.setText(pass)

        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                saveToPrefs("email", s?.toString() ?: "")
            }
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                saveToPrefs("password", s?.toString() ?: "")
            }
        })
    }

    private fun saveToPrefs(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun signin(view: View) {
        if(email.text.toString().isNotEmpty() || password.text.toString().isNotEmpty()){
            val intent = Intent(this, ChatsActivity::class.java)
            startActivity(intent)
        }
        else {
            val alert = AlertDialog.Builder(this)
                .setTitle("Ошибка")
                .setMessage("У вас есть незаполненные поля")
                .setPositiveButton("Ok", null)
                .create()
                .show()
        }
    }
}