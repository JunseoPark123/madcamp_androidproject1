package com.example.madcamp_androidproject

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val contactList: List<Contact>) : RecyclerView.Adapter<ContactAdapter.ViewHolder>(),
    Filterable {

    var filteredList = contactList

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.textViewName)
        val phoneNumberTextView: TextView = view.findViewById(R.id.textViewPhoneNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = filteredList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = filteredList[position]
        holder.nameTextView.text = contact.name
        holder.phoneNumberTextView.text = contact.phoneNumber
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                try {
                    val charSearch = constraint.toString()
                    filteredList = when {
                        charSearch.isEmpty() || !charSearch.isCompleteHangul() -> {
                            // 검색어가 비어 있거나 미완성 한글일 경우 전체 리스트 반환
                            contactList
                        }
                        else -> {
                            // 검색어가 이름에 포함된 연락처를 필터링
                            contactList.filter { it.name.contains(charSearch) }
                        }
                    }
                    val filterResults = FilterResults()
                    filterResults.values = filteredList
                    return filterResults
                } catch (e: Exception) {
                    Log.e("Filter", "Error during filter", e)
                    throw e
                }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as ArrayList<Contact>
                notifyDataSetChanged()
            }
        }
    }
}

// isCompleteHangul() 함수 추가
fun CharSequence.isCompleteHangul(): Boolean {
    return this.matches(Regex("^[가-힣]*\$"))
}