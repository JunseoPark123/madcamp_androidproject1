package com.example.madcamp_androidproject

data class Question(
    val id: Int,
    val question: String,
    val optionOne:String,
    val optionTwo:String,
    val optionThree:String,
    val optionFour:String,
    val correctAnswer:Int,
    val word: String,
    val meaning: String
)
