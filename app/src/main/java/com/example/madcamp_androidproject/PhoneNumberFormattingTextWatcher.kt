package com.example.madcamp_androidproject

// PhoneNumberFormattingTextWatcher.kt
import android.text.Editable
import android.text.TextWatcher

class PhoneNumberFormattingTextWatcher : TextWatcher {
    private var isFormatting = false
    private var deletingHyphen = false
    private var hyphenStart = false
    private var lastHyphenPosition = -1

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        // 하이픈 삭제 중인지 확인
        val length = s.length
        deletingHyphen = count == 1 && after == 0 && s[start] == '-' &&
                ((start > 0 && s[start - 1] != '-') || (length > start + 1 && s[start + 1] != '-'))
        if (deletingHyphen) lastHyphenPosition = start
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        // 하이픈이 입력 시작점에 추가되는지 확인
        hyphenStart = count == 1 && s[start] == '-'
    }

    override fun afterTextChanged(s: Editable) {
        // 이미 포맷 중이거나 하이픈을 시작하면 무시
        if (isFormatting || hyphenStart) return
        isFormatting = true

        // 하이픈을 삭제하는 경우
        if (deletingHyphen && lastHyphenPosition > 0) {
            if (lastHyphenPosition < s.length) {
                s.delete(lastHyphenPosition, lastHyphenPosition + 1)
            }
        } else {
            // 전화번호에 하이픈 추가
            val formattedNumber = formatPhoneNumber(s)
            s.replace(0, s.length, formattedNumber)
        }

        isFormatting = false
    }

    private fun formatPhoneNumber(s: CharSequence): String {
        // 숫자만 추출
        val numbersOnly = s.toString().filter { it.isDigit() }
        val builder = StringBuilder(numbersOnly)

        // 하이픈을 추가하는 조건에 따라 문자열을 조정
        if (builder.length > 7) {
            builder.insert(7, '-')
        }
        if (builder.length > 3) {
            builder.insert(3, '-')
        }

        // 전화번호 포맷에 맞춘 문자열 반환
        return builder.toString()
    }
}
