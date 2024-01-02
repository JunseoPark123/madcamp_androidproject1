package com.example.madcamp_androidproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.SearchView
import androidx.activity.result.contract.ActivityResultContracts
import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageButton
import androidx.activity.result.ActivityResultLauncher
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode


class ContactActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var contactAdapter: ContactAdapter
    private var contactList: MutableList<Contact> = mutableListOf()
    private lateinit var editContactLauncher: ActivityResultLauncher<Intent>


    // addContactLauncher를 val로 선언하고 클래스의 프로퍼티로 만듭니다.
    private val addContactLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            if (data != null) {
                val newName = data.getStringExtra("newName")
                val newPhoneNumber = data.getStringExtra("newPhoneNumber")
                if (newName != null && newPhoneNumber != null) {
                    val newContact = Contact(newName, newPhoneNumber)
                    contactList.add(newContact)
                    contactList.sort()
                    contactAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val jsonLoader = JsonLoader(this)
        contactList = jsonLoader.loadContacts().toMutableList()

        // ContactAdapter를 초기화하고 클릭 리스너를 설정
        contactAdapter = ContactAdapter(contactList) { contact, position ->
            val intent = Intent(this, DetailContactActivity::class.java).apply {
                putExtra("CONTACT", contact)
                putExtra("CONTACT_LIST", contactList?.toTypedArray())
                putExtra("ADAPTER_POSITION", position)
            }
            editContactLauncher.launch(intent)
        }
        recyclerView.adapter = contactAdapter

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                contactAdapter.filter.filter(newText)
                return false
            }
        })

        editContactLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                @Suppress("DEPRECATION")
                val updatedContact: Contact? = result.data?.getParcelableExtra("UPDATED_CONTACT")
                updatedContact?.let {
                    // 연락처 리스트에서 해당 연락처를 찾아 업데이트합니다.
                    val index = contactList.indexOfFirst { it.phoneNumber == updatedContact.phoneNumber }
                    if (index != -1) {
                        contactList[index] = updatedContact
                        contactList.sortWith(compareBy { it.name })
                        contactAdapter.notifyItemChanged(index)
                    }
                }
            } else if (result.resultCode == RESULT_FIRST_USER) {
                // 연락처 삭제 처리
                val deletedContact: Contact? = result.data?.getParcelableExtra("DELETED_CONTACT")
                deletedContact?.let {
                    // 연락처 리스트에서 해당 연락처를 찾아 삭제합니다.
                    val index = contactList.indexOfFirst { it.phoneNumber == deletedContact.phoneNumber }
                    if (index != -1) {
                        contactList.removeAt(index)
                        contactAdapter.notifyItemRemoved(index)
                    }
                }
            }
        }

        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_phonenumber -> {
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



        // 연락처 추가 버튼 클릭 리스너
        val goToAddButton = findViewById<ImageButton>(R.id.goToAddButton)
        goToAddButton.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            addContactLauncher.launch(intent)  // 올바른 launcher를 사용합니다.
        }
    }
}

data class Contact(val name: String, val phoneNumber: String) : Parcelable, Comparable<Contact> {

    override fun compareTo(other: Contact): Int {
        return name.compareTo(other.name)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(phoneNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contact> {
        override fun createFromParcel(parcel: Parcel): Contact {
            val name = parcel.readString() ?: ""
            val phoneNumber = parcel.readString() ?: ""
            return Contact(name, phoneNumber)
        }

        override fun newArray(size: Int): Array<Contact?> {
            return arrayOfNulls(size)
        }
    }
}


