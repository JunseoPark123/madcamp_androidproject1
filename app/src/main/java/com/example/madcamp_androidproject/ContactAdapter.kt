package com.example.madcamp_androidproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val contactList: List<Contact>, private val onContactClicked: (Contact, Int) -> Unit) : RecyclerView.Adapter<ContactAdapter.ViewHolder>(),
    Filterable {

    var filteredList = contactList

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contactIcon: TextView = view.findViewById(R.id.contactIcon)
        val nameTextView: TextView = view.findViewById(R.id.textViewName)
        val phoneNumberTextView: TextView = view.findViewById(R.id.textViewPhoneNumber)
        val callButton: ImageButton = view.findViewById<ImageButton>(R.id.call_button)
        val messageButton: ImageButton = view.findViewById<ImageButton>(R.id.message_button)

        init {
            view.setOnClickListener {
                // 여기에서 클릭된 아이템에 대한 동작을 정의합니다.
                // 예: 상세 정보 액티비티로 이동
                val intent = Intent(view.context, DetailContactActivity::class.java)
                intent.putExtra("CONTACT", contactList[adapterPosition])
                view.context.startActivity(intent)
                onContactClicked(contactList[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = filteredList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = filteredList[position]
        holder.contactIcon.text = contact.name.first().toString()
        holder.nameTextView.text = contact.name
        holder.phoneNumberTextView.text = contact.phoneNumber

        // 전화 버튼에 클릭 리스너 설정
        holder.callButton.setOnClickListener {
            val context = holder.itemView.context
            val phoneNumber = contact.phoneNumber

            // Intent.ACTION_DIAL 사용하여 다이얼 패드 열기
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            context.startActivity(intent)
        }

        // 메시지 버튼에 클릭 리스너 설정
        holder.messageButton.setOnClickListener {
            val context = holder.itemView.context
            val phoneNumber = contact.phoneNumber

            // Intent.ACTION_SENDTO 사용하여 SMS 앱 열기
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:$phoneNumber") // 여기에는 sms: 혹은 smsto: 를 사용할 수 있습니다.
            }
            // 인텐트에 사용 가능한 앱이 있는지 확인하고 있다면 앱을 시작합니다.
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            } else {
                Log.e("ContactAdapter", "No activity found to handle SMS intent")
            }
        }

        holder.itemView.setOnClickListener {
            onContactClicked(contact, position) // 클릭 이벤트에 리스너를 연결
        }
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