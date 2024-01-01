package com.example.madcamp_androidproject
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddContactActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        nameEditText = findViewById(R.id.editTextName)
        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber)
        addButton = findViewById(R.id.AddButton)

        phoneNumberEditText.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        addButton.setOnClickListener {
            // "확인" 버튼 클릭 시 동작
            val newName = nameEditText.text.toString()
            val newPhoneNumber = phoneNumberEditText.text.toString()

            // 데이터를 Intent에 담아서 결과로 반환
            val resultIntent = Intent()
            resultIntent.putExtra("newName", newName)
            resultIntent.putExtra("newPhoneNumber", newPhoneNumber)

            // 결과 설정 및 액티비티 종료
            setResult(RESULT_OK, resultIntent)
            finish()
        }

    }
}

