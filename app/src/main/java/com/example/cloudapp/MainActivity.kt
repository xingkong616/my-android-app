package com.example.cloudapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var clickCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val message: TextView = findViewById(R.id.message)
        val button: Button = findViewById(R.id.button)

        button.setOnClickListener {
            clickCount++
            message.text = getString(R.string.click_count, clickCount)
        }
    }
}
