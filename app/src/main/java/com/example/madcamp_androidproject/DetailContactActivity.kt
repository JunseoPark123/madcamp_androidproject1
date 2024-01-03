package com.example.madcamp_androidproject

import android.content.Intent
import android.os.Bundle

import android.view.View
import android.widget.Button

import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

import androidx.cardview.widget.CardView

import androidx.appcompat.app.AppCompatActivity


class DetailContactActivity : AppCompatActivity() {
    private lateinit var contactInitialTextView: TextView
    private lateinit var nameEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var saveButton: ImageButton
    private lateinit var deleteButton: ImageButton
    private lateinit var goBackButton: ImageButton
    private lateinit var deleteConfirmationCardView: CardView
    private lateinit var darkOverlay: View



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_contact)

        contactInitialTextView = findViewById(R.id.contactInitial)
        nameEditText = findViewById(R.id.editTextName)
        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber)
        saveButton = findViewById<ImageButton>(R.id.saveButton)
        deleteButton = findViewById<ImageButton>(R.id.delete_button)
        goBackButton = findViewById<ImageButton>(R.id.GoBack_button)
        // 삭제 확인 및 오버레이 뷰 초기화
        darkOverlay = findViewById(R.id.darkOverlay)
        deleteConfirmationCardView = findViewById(R.id.deleteConfirmationCardView)



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

        // 삭제 버튼 클릭 리스너
        deleteButton.setOnClickListener {
            // 오버레이 및 삭제 확인 카드뷰 표시
            darkOverlay.visibility = View.VISIBLE
            deleteConfirmationCardView.visibility = View.VISIBLE
        }


        // "확인" 버튼 클릭 리스너 설정
        val buttonConfirmDelete: Button = findViewById(R.id.buttonConfirmDelete)
        buttonConfirmDelete.setOnClickListener {
            // 연락처 삭제 로직을 여기에 추가합니다.
            // 예를 들어, 삭제 결과를 메인 액티비티에 전달합니다.
            val resultIntent = Intent().apply {
                putExtra("DELETED_CONTACT", contact)
                setResult(RESULT_FIRST_USER, this)
            }
            finish()
            darkOverlay.visibility = View.GONE
            deleteConfirmationCardView.visibility = View.GONE
        }

        // "취소" 버튼 클릭 리스너 설정
        val buttonCancelDelete: Button = findViewById(R.id.buttonCancelDelete)
        buttonCancelDelete.setOnClickListener {
            // CardView를 숨깁니다.
            darkOverlay.visibility = View.GONE
            deleteConfirmationCardView.visibility = View.GONE
        }

        goBackButton.setOnClickListener {
            finish()
        }
    }
}