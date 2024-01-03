package com.example.madcamp_androidproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class QuizActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val btnQuiz : Button = findViewById(R.id.btn_vocaquiz)
        btnQuiz.setOnClickListener {
            val intent = Intent(this@QuizActivity, DayActivity::class.java)
            startActivity(intent)
        }

        val btnVoca : Button = findViewById(R.id.btn_toeic_myvoca)
        btnVoca.setOnClickListener {
            val intent = Intent(this@QuizActivity, MyVocaActivity::class.java)
            startActivity(intent)
        }

        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavView.selectedItemId = R.id.navigation_english
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