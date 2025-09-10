package com.example.kardashin_b2_v3

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater

class ChatsActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private val items = listOf("Chinonso James", "John Joshua", "Raph Ron", "Joy Ezekiel", "Joy Ezekiel")
    private val items_text = listOf("Alright, I wll be waiting",
        "Thanks for your service",
        "Thanks for your service",
        "Thanks for your service",
        "Thanks for your service")
    private val items_img = listOf(
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3,
        R.drawable.img4,
        R.drawable.img5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats)

        var hour = findViewById<TextView>(R.id.hour)
        var minute = findViewById<TextView>(R.id.minute)

        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        hour.text = currentHour.toString()
        minute.text = currentMinute.toString()

        listView = findViewById(R.id.listView)

        val adapter = CustomAdapter(this, items, items_text, items_img)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, InfoActivity::class.java)
            intent.putExtra("title", items[position])
            intent.putExtra("image", items_img[position])
            startActivity(intent)
        }
    }

    fun signin(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}

class CustomAdapter(
    context: Context,
    private val titles: List<String>,
    private val text: List<String>,
    private val images: List<Int>
) : ArrayAdapter<String>(context, R.layout.list_item, titles) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val rowView = convertView ?: inflater.inflate(R.layout.list_item, parent, false)

        val titleView = rowView.findViewById<TextView>(R.id.itemText)
        val imageView = rowView.findViewById<ImageView>(R.id.itemPng)
        val textView = rowView.findViewById<TextView>(R.id.text)

        titleView.text = titles[position]
        imageView.setImageResource(images[position])
        textView.text = text[position]

        return rowView
    }
}
