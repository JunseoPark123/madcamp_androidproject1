package com.example.madcamp_androidproject
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.nio.charset.Charset
import android.widget.SearchView

data class Contact(val name: String, val phoneNumber: String)
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var contactAdapter: ContactAdapter
    private var contactList: List<Contact> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        contactList = loadContacts()
        contactAdapter = ContactAdapter(contactList)
        recyclerView.adapter = contactAdapter


        // 검색바 구현
        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                contactAdapter.filter.filter(newText)
                return false
            }
        })
    }

    private fun loadContacts(): List<Contact> {
        val contacts = mutableListOf<Contact>()
        val jsonString = assets.open("phone_book.json").bufferedReader(Charset.forName("UTF-8")).use { it.readText() }
        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val name = jsonObject.getString("name")
            val phoneNumber = jsonObject.getString("phone_number")
            contacts.add(Contact(name, phoneNumber))
        }
        return contacts
    }
}
