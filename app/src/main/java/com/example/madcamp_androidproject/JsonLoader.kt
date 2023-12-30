package com.example.madcamp_androidproject

import android.content.Context
import org.json.JSONArray
import java.nio.charset.Charset

class JsonLoader(private val context: Context) {
    fun loadContacts(): List<Contact> {
        val contacts = mutableListOf<Contact>()
        try {
            val jsonString = context.assets.open("phone_book.json").bufferedReader(Charset.forName("UTF-8")).use { it.readText() }
            val jsonArray = JSONArray(jsonString)

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val name = jsonObject.getString("name")
                val phoneNumber = jsonObject.getString("phone_number")
                contacts.add(Contact(name, phoneNumber))
            }

            // 한글 이름을 기준으로 정렬
            contacts.sort()
        } catch (e: Exception) {
            // 예외 처리: JSON 파일을 읽어오는 동안 오류가 발생할 수 있으므로 예외 처리가 필요합니다.
            e.printStackTrace()
        }
        return contacts
    }
}

