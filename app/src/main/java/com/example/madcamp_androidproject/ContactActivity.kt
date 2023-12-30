package com.example.madcamp_androidproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.SearchView
import androidx.activity.result.contract.ActivityResultContracts

data class Contact(val name: String, val phoneNumber: String) : Comparable<Contact> {
    override fun compareTo(other: Contact): Int {
        return name.compareTo(other.name)
    }
}

class ContactActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var contactAdapter: ContactAdapter
    private var contactList: MutableList<Contact> = mutableListOf()

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

        contactAdapter = ContactAdapter(contactList)
        recyclerView.adapter = contactAdapter

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                contactAdapter.filter.filter(newText)
                return false
            }
        })

        val goToAddButton: Button = findViewById(R.id.goToAddButton)
        goToAddButton.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            //startActivity(intent)
            addContactLauncher.launch(intent)  // 이제 smart cast 오류가 발생하지 않습니다.
        }
    }

    private fun addContactSorted(contactList: List<Contact>, newContact: Contact): List<Contact> {
        val newList = contactList.toMutableList()
        newList.add(newContact)
        newList.sort()
        return newList
    }
}
