package com.example.madcamp_androidproject
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class AddContactActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var addButton: Button
    private lateinit var bottomNavView: BottomNavigationView // Declare here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        // Initialize EditTexts and Button
        nameEditText = findViewById(R.id.editTextName)
        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber)
        addButton = findViewById(R.id.AddButton) // Make sure the ID matches your layout

        // Initialize BottomNavigationView

        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_phonenumber -> {
                    val intent = Intent(this, ContactActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }

                R.id.navigation_photo -> {
                    val intent = Intent(this, PhotoActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }

                R.id.navigation_english -> {
                    val intent = Intent(this, QuizActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }

                else -> false
            }
        }

        // Set up the addButton's onClickListener
        addButton.setOnClickListener {
            // "확인" 버튼 클릭 시 동작
            val newName = nameEditText.text.toString()
            val newPhoneNumber = phoneNumberEditText.text.toString()

            // 데이터를 Intent에 담아서 결과로 반환
            val resultIntent = Intent().apply {
                putExtra("newName", newName)
                putExtra("newPhoneNumber", newPhoneNumber)
            }

            // 결과 설정 및 액티비티 종료
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}

