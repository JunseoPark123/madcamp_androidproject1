package com.example.madcamp_androidproject
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class AddContactActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var addButton: ImageButton
    private lateinit var goBackButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        nameEditText = findViewById(R.id.editTextName)
        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber)
        addButton = findViewById<ImageButton>(R.id.AddButton)
        goBackButton = findViewById<ImageButton>(R.id.GoBack_button)

        phoneNumberEditText.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        addButton.setOnClickListener {
            // "확인" 버튼 클릭 시 동작
            val newName = nameEditText.text.toString()
            val newPhoneNumber = phoneNumberEditText.text.toString()

            // 이름이 비어있지 않은 경우에만 데이터를 추가합니다.
            if (newName.isNotBlank()) {
                // 데이터를 Intent에 담아서 결과로 반환
                val resultIntent = Intent()
                resultIntent.putExtra("newName", newName)
                resultIntent.putExtra("newPhoneNumber", newPhoneNumber)

                // 결과 설정 및 액티비티 종료
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                // 이름이 비어 있으면 사용자에게 피드백을 줍니다.
                Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
                // 여기서는 아무것도 하지 않고 종료하지 않습니다.
            }
        }

        goBackButton.setOnClickListener {
            finish()
        }

        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavView.selectedItemId = R.id.navigation_photo
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