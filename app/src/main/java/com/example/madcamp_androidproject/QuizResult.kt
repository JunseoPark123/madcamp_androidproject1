package com.example.madcamp_androidproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizResult(
    val word: String,
    val meaning: String,
    val isCorrect: Boolean
) : Parcelable