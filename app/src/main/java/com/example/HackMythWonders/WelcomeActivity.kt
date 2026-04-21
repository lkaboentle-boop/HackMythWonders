package com.example.HackMythWonders

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeActivity : AppCompatActivity() {

    private lateinit var btnStart: Button
    private lateinit var tvTitle: TextView
    private lateinit var tvSubTitle: TextView
    private lateinit var tvWelcome: TextView
    private lateinit var tvDescription: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnStart = findViewById<Button>(R.id.btnStart)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        findViewById<TextView>(R.id.tvSubTitle)
        findViewById<TextView>(R.id.tvWelcome)
        findViewById<TextView>(R.id.tvDescription)

        // Set click listener to navigate to QuizActivity
        btnStart.setOnClickListener {
            Toast.makeText(this, "Let's get started!", Toast.LENGTH_SHORT).show()

            //Proceed to QuizActivity
            val intent = Intent( this, QuizActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }
}