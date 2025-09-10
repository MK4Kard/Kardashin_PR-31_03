package com.example.kardashin_b2_v3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        var hour = findViewById<TextView>(R.id.hour)
        var minute = findViewById<TextView>(R.id.minute)

        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        hour.text = currentHour.toString()
        minute.text = currentMinute.toString()

        val title = intent.getStringExtra("title")
        val image = intent.getIntExtra("image", 0)

        val titleView = findViewById<TextView>(R.id.itemText)
        val imageView = findViewById<ImageView>(R.id.itemPng)

        titleView.text = title
        imageView.setImageResource(image)
    }

    fun back(view: View) {
        val intent = Intent(this, ChatsActivity::class.java)
        startActivity(intent)
    }

}