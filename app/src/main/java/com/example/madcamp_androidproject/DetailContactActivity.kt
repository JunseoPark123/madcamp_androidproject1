package com.example.madcamp_androidproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class DetailContactActivity : AppCompatActivity() {
    private lateinit var contactInitialTextView: TextView
    private lateinit var nameEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var saveButton: ImageButton
    private lateinit var deleteButton: ImageButton
    private lateinit var goBackButton: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_contact)

        contactInitialTextView = findViewById(R.id.contactInitial)
        nameEditText = findViewById(R.id.editTextName)
        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber)
        saveButton = findViewById<ImageButton>(R.id.saveButton)
        deleteButton = findViewById<ImageButton>(R.id.delete_button)
        goBackButton = findViewById<ImageButton>(R.id.GoBack_button)


        // Intent에서 연락처 정보 가져오기
        @Suppress("DEPRECATION")
        val contact = intent.getParcelableExtra<Contact>("CONTACT")
        contact?.let {
            nameEditText.setText(it.name)
            phoneNumberEditText.setText(it.phoneNumber)
            contactInitialTextView.text = it.name.first().toString()
        }

        phoneNumberEditText.addTextChangedListener(PhoneNumberFormattingTextWatcher())


        // 저장 버튼 클릭 리스너
        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val phoneNumber = phoneNumberEditText.text.toString()

            // 이름이 비어 있는지 확인
            if (name.isBlank()) {
                // 이름이 비어 있으면 토스트 메시지를 표시
                Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                // 이름이 있으면 연락처를 수정하고 결과를 Intent에 추가
                val updatedContact = Contact(name, phoneNumber)
                val resultIntent = Intent().apply {
                    putExtra("UPDATED_CONTACT", updatedContact)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

        deleteButton.setOnClickListener {
            // 삭제할 연락처의 정보를 결과로 설정하고 액티비티를 종료합니다.
            val resultIntent = Intent()
            resultIntent.putExtra("DELETED_CONTACT", contact)
            setResult(RESULT_FIRST_USER, resultIntent) // RESULT_OK 대신 다른 결과 코드를 사용합니다.
            finish()
        }

        goBackButton.setOnClickListener {
            finish()
        }
    }
}
