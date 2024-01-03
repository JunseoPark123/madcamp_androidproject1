package com.example.madcamp_androidproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONArray

class MyVocaActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var vocabularyAdapter: VocabularyAdapter
    private val vocabularyList = mutableListOf<VocabularyItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_voca)

        recyclerView = findViewById(R.id.rvMyVocabulary)
        recyclerView.layoutManager = LinearLayoutManager(this)
        vocabularyAdapter = VocabularyAdapter(vocabularyList)
        recyclerView.adapter = vocabularyAdapter

        loadVocabularyData()

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

    private fun loadVocabularyData() {
        val sharedPreferences = getSharedPreferences("WrongAnswers", Context.MODE_PRIVATE)
        val wrongAnswers = sharedPreferences.getString("wrongAnswers", "[]")
        val jsonArray = JSONArray(wrongAnswers)

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val day = jsonObject.getString("day")
            val word = jsonObject.getString("word")
            val meaning = jsonObject.getString("meaning")
            vocabularyList.add(VocabularyItem(day, word, meaning))
        }

        vocabularyAdapter.notifyDataSetChanged()
    }


}