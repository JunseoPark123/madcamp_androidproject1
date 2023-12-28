package com.example.madcamp_androidproject
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.nio.charset.Charset
import android.view.LayoutInflater


data class Contact(val name: String, val phoneNumber: String)

class ContactAdapter(private val contactList: List<Contact>) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.textViewName)
        val phoneNumberTextView: TextView = view.findViewById(R.id.textViewPhoneNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactList[position]
        holder.nameTextView.text = contact.name
        holder.phoneNumberTextView.text = contact.phoneNumber
    }

    override fun getItemCount() = contactList.size
}
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
