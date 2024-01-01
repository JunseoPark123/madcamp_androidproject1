package com.example.madcamp_androidproject

import android.content.Context
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.InputStream
import kotlin.random.Random


object Constants {

    // TODO  Create a constant variables which we required in the result screen
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
    fun getQuestions(context: Context): ArrayList<Question> {
        val questionsList = ArrayList<Question>()
        val inputStream: InputStream = context.assets.open("toeic_word.xlsx")
        val workbook = WorkbookFactory.create(inputStream)
        val sheet = workbook.getSheetAt(0)
        val day1Words = ArrayList<String>()
        val day1Meanings = ArrayList<String>()

        for (row in sheet) {
            if (row.getCell(0).stringCellValue == "day1") {
                day1Words.add(row.getCell(1).stringCellValue)
                day1Meanings.add(row.getCell(2).stringCellValue)
            }
        }

        for (i in 1..10) {
            val correctAnswerIndex = Random.nextInt(day1Words.size)
            val questionWord = day1Words[correctAnswerIndex]
            val correctAnswer = day1Meanings[correctAnswerIndex]

            // 올바른 답변과 다른 답변들을 섞음
            val options = day1Meanings.shuffled().take(3).toMutableList()
            options.add(correctAnswer)
            options.shuffle()

            val correctOptionIndex = options.indexOf(correctAnswer) + 1

            questionsList.add(
                Question(
                    i, "What is the meaning of '$questionWord'?",
                    options[0], options[1], options[2], options[3], correctOptionIndex
                )
            )
        }

        inputStream.close()
        return questionsList
    }
}