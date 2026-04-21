package com.example.HackMythWonders

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnRestart = findViewById<Button>(R.id.btnRestart)
        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL", 0)
        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvFeedback = findViewById<TextView>(R.id.tvFeedback)
        val btnReview = findViewById<Button>(R.id.btnReview)
        val tvReview = findViewById<TextView>(R.id.tvReview)
        tvScore.text = "Your Score: $score/$total"

        if (score >= total * 0.7) {
            tvFeedback.setText("Congratulations! You're a Master Hacker Bestie!")
        } else {
            tvFeedback.setText("Keep trying! You can do better!")
        }
        
        //Questions List
        val questions = listOf(
            QuizQuestion(
                question = "Does the last person to eat or speak consider as the King?",
                correctAnswer = false,
                explanation = "There is no such thing as a king in life, it only depends on the culture."
            ),
            QuizQuestion(
                question = "Does eating carrots improves your eyesight?",
                correctAnswer = true,
                explanation = "Carrots contain vitamin A, which can help improve your eyesight."
            ),
            QuizQuestion(
                question = "Do goldfish only have a 3-second memory?",
                correctAnswer = false,
                explanation = "Goldfish can remember things for months."
            ),
            QuizQuestion(
                question = "Does toothpaste remove all stains from clothes?",
                correctAnswer = false,
                explanation = "It may help small stains, but is not a universal solution."
            ),
            QuizQuestion(
                question = "Does teaching someone help you understand better?",
                correctAnswer = true,
                explanation = "Explaining reinforces your own understanding."
            ),
            QuizQuestion(
                question = "Does taking short breaks improve your focus?",
                correctAnswer = true,
                explanation = "Breaks help reset attention and reduce mental fatigue."
            ),
            QuizQuestion(
                question = "Is natural sugar always healthier than added sugar?",
                correctAnswer = false,
                explanation = "Too much sugar is unhealthy regardless of the source."
            ),
            QuizQuestion(
                question = "Can drinking water before meals help control appetite?",
                correctAnswer = true,
                explanation = "It can make you feel fuller and reduce overeating."
            ),
            QuizQuestion(
                question = "Does a rubber band around a jar lid help open it?",
                correctAnswer = true,
                explanation = "It improves grip, making it easier to twist open the jar."
            ),
            QuizQuestion(
                question = "Does multitasking improve productivity?",
                correctAnswer = false,
                explanation = "Switching tasks frequently reduces efficiency."
            ),
            QuizQuestion(
                question = "Does putting your phone in rice fix water damage?",
                correctAnswer = false,
                explanation = "It doesn't fully remove moisture and can cause more damage."
            )
        )

        btnReview.setOnClickListener {
            val reviewText = StringBuilder()
            questions.forEachIndexed { index, question ->
                val answer = if (question.correctAnswer) "Hack (True)" else "Myth (False)"
                reviewText.append("Q${index + 1}: ${question.question}\n")
                reviewText.append("Answer: $answer\n")
                reviewText.append("Explanation: ${question.explanation}\n\n")
            }
            //Show results in ScrollView TextView in ResultActivity
            tvReview.text = reviewText.toString()
        }


        btnRestart.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
