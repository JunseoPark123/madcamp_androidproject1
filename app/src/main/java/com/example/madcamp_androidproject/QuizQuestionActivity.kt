package com.example.madcamp_androidproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class QuizQuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

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