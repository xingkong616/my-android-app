package com.example.cloudapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var greetings: Array<String>
    private var lastIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        greetings = resources.getStringArray(R.array.greetings)

        val greetingView: TextView = findViewById(R.id.greeting)
        val button: Button = findViewById(R.id.button)

        showRandomGreeting(greetingView)
        button.setOnClickListener { showRandomGreeting(greetingView) }
    }

    private fun showRandomGreeting(view: TextView) {
        var idx = (greetings.indices).random()
        if (greetings.size > 1) {
            while (idx == lastIndex) {
                idx = (greetings.indices).random()
            }
        }
        lastIndex = idx
        view.text = greetings[idx]
    }
}
