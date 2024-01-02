import android.content.Context
import android.util.Log
import com.example.madcamp_androidproject.Question
import com.example.madcamp_androidproject.ToeicWord
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.io.InputStream
import java.nio.charset.Charset
import kotlin.random.Random

object Constants {

    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(context: Context, selectedDay: String): ArrayList<Question> {
        val questionsList = ArrayList<Question>()
        val usedWords = mutableSetOf<String>() // 이미 사용된 단어를 추적하기 위한 집합

        try {
            val jsonString =
                context.assets.open("toeic_word.json").bufferedReader(Charset.forName("UTF-8"))
                    .use { it.readText() }
            Log.d("Constants", "JSON loaded successfully")
            val jsonArray = JSONArray(jsonString)
            val day1Words = mutableListOf<ToeicWord>()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                if (jsonObject.getString("Day") == selectedDay) {
                    val word = jsonObject.getString("단어")
                    val meaning = jsonObject.getString("뜻")
                    day1Words.add(ToeicWord(jsonObject.getString("Day"), word, meaning))
                }
            }

            while (questionsList.size < 10 && day1Words.isNotEmpty()) {
                val correctAnswerIndex = Random.nextInt(day1Words.size)
                val selectedWord = day1Words[correctAnswerIndex]

                if (usedWords.add(selectedWord.word)) { // 이미 사용된 단어가 아닌 경우에만 진행
                    val questionWord = selectedWord.meaning
                    val correctAnswer = selectedWord.word

                    // 옵션 생성
                    val options = day1Words.filter { it.word != correctAnswer }
                        .shuffled()
                        .take(3)
                        .map { it.word }
                        .toMutableList()
                    options.add(correctAnswer)
                    options.shuffle()

                    val correctOptionIndex = options.indexOf(correctAnswer) + 1

                    questionsList.add(
                        Question(
                            questionsList.size + 1,
                            "Find the correct English word:\n'$questionWord'",
                            options[0], options[1], options[2], options[3], correctOptionIndex
                        )
                    )

                    // 사용된 단어 제거
                    day1Words.removeAt(correctAnswerIndex)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Constants", "Error loading JSON", e)
        }

        return questionsList
    }
}