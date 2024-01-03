package com.example.madcamp_androidproject

import Constants
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvResult: TextView = findViewById(R.id.tv_result)
        val tvScore: TextView = findViewById(R.id.tv_score)
        val btnFinish: Button = findViewById(R.id.btn_finish)

        val selectedDay = intent.getStringExtra("selectedDay") ?: "day1"
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        val incorrectAnswers = totalQuestions - correctAnswers
        val quizResults: ArrayList<QuizResult> = intent.getSerializableExtra("QuizResults") as ArrayList<QuizResult>

        // Set Day and Score information
        tvResult.text = "Results for $selectedDay"
        tvScore.text = "Correct: $correctAnswers, Incorrect: $incorrectAnswers"

        // RecyclerView setup
        val rvIncorrectWords: RecyclerView = findViewById(R.id.rv_incorrect_words)
        rvIncorrectWords.layoutManager = LinearLayoutManager(this)
        rvIncorrectWords.adapter = QuizResultAdapter(quizResults)

        btnFinish.setOnClickListener {
            startActivity(Intent(this@ResultActivity, QuizActivity::class.java))
        }

        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_phonenumber -> {
                    val intent = Intent(this, ContactActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.navigation_photo -> {
                    val intent = Intent(this, PhotoActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.navigation_english -> {
                    val intent = Intent(this, QuizActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }
    }


}