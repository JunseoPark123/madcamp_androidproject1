package com.example.madcamp_androidproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class DetailContactActivity : AppCompatActivity() {
    private lateinit var contactInitialTextView: TextView
    private lateinit var nameEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var deleteButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_contact)

        contactInitialTextView = findViewById(R.id.contactInitial)
        nameEditText = findViewById(R.id.editTextName)
        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber)
        saveButton = findViewById(R.id.saveButton)
        deleteButton = findViewById(R.id.delete_button)


        // Intent에서 연락처 정보 가져오기
        @Suppress("DEPRECATION")
        val contact = intent.getParcelableExtra<Contact>("CONTACT")
        contact?.let {
            nameEditText.setText(it.name)
            phoneNumberEditText.setText(it.phoneNumber)
            contactInitialTextView.text = it.name.first().toString()
        }

        // 저장 버튼 클릭 리스너
        saveButton.setOnClickListener {
            val updatedContact = Contact(
                nameEditText.text.toString(),
                phoneNumberEditText.text.toString()
            )

            // 연락처를 수정하고 결과를 Intent에 추가
            val resultIntent = Intent()
            resultIntent.putExtra("UPDATED_CONTACT", updatedContact)
            setResult(RESULT_OK, resultIntent)

            finish()
        }

        deleteButton.setOnClickListener {
            // 삭제할 연락처의 정보를 결과로 설정하고 액티비티를 종료합니다.
            val resultIntent = Intent()
            resultIntent.putExtra("DELETED_CONTACT", contact)
            setResult(RESULT_FIRST_USER, resultIntent) // RESULT_OK 대신 다른 결과 코드를 사용합니다.
            finish()
        }
    }
}
