package com.example.HackMythWonders

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {

    private lateinit var tvQuestion: TextView
    private lateinit var btnMyth: Button
    private lateinit var btnHack: Button

    private var currentQuestionIndex = 0
    private var score = 0
    private val userResults = mutableListOf<Boolean>()


    // List of quiz questions
    private val questions = listOf(
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvQuestion = findViewById(R.id.tvQuestion)
        btnMyth = findViewById(R.id.btnMyth)
        btnHack = findViewById(R.id.btnHack)
        displayQuestion()


        btnMyth.setOnClickListener {
            checkAnswer(false)
        }

        btnHack.setOnClickListener {
            checkAnswer(true)
        }
    }

    private fun displayQuestion() {
        if (currentQuestionIndex < questions.size) {
            tvQuestion.text = questions[currentQuestionIndex].question
        } else {
            finishQuiz()
        }
    }

    // Check the user's answer and provide feedback
    private fun checkAnswer(userAnswer: Boolean) {
        if (currentQuestionIndex >= questions.size) return

        val question = questions[currentQuestionIndex]
        // Check if the user's answer matches the correct answer
        if (userAnswer == question.correctAnswer) {
            score++
            Toast.makeText(this, "That's Correct Bestie!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Sorry, That's Wrong Bestie!", Toast.LENGTH_SHORT).show()
        }

        currentQuestionIndex++
        displayQuestion()
    }

    // Finish the quiz and navigate to the ResultActivity
    private fun finishQuiz() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("SCORE", score)
        intent.putExtra("TOTAL", questions.size)
        startActivity(intent)
        this.finish()
    }
}
data class QuizQuestion(
    val question: String,
    val correctAnswer: Boolean, // true for Hack, false for Myth
    val explanation: String
)