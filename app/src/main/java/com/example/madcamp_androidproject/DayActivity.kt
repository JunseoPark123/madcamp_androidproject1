package com.example.madcamp_androidproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.google.android.material.bottomnavigation.BottomNavigationView

class DayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day)

        val spinner: Spinner = findViewById(R.id.spinner_day)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, (1..30).map { "day$it" })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val buttonStartQuiz: Button = findViewById(R.id.button_start_quiz)
        buttonStartQuiz.setOnClickListener {
            val selectedDay = spinner.selectedItem.toString()
            val intent = Intent(this, QuizQuestionActivity::class.java)
            intent.putExtra("selectedDay", selectedDay)
            startActivity(intent)
        }

//        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_navigation)
//        bottomNavView.selectedItemId = R.id.navigation_english
//        bottomNavView.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.navigation_phonenumber -> {
//                    val intent = Intent(this, ContactActivity::class.java)
//                    startActivity(intent)
//                    true
//                }
//
//                R.id.navigation_photo -> {
//                    val intent = Intent(this, PhotoActivity::class.java)
//                    startActivity(intent)
//                    true
//                }
//
//                R.id.navigation_english -> {
//                    val intent = Intent(this, QuizActivity::class.java)
//                    startActivity(intent)
//                    true
//                }
//
//                else -> false
//            }
//        }
    }
}
