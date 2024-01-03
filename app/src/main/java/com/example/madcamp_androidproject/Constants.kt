import android.content.Context
import android.util.Log
import com.example.madcamp_androidproject.Question
import com.example.madcamp_androidproject.ToeicWord
import org.json.JSONArray
import java.nio.charset.Charset
import kotlin.random.Random

object Constants {

    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(context: Context, selectedDay: String): ArrayList<Question> {
        val questionsList = ArrayList<Question>()
        val usedWords = mutableSetOf<String>() // 이미 사용된 단어를 추적하기 위한 집합

        try {
            val jsonString = context.assets.open("toeic_word.json")
                .bufferedReader(Charset.forName("UTF-8")).use { it.readText() }
            Log.d("Constants", "JSON loaded successfully")
            val jsonArray = JSONArray(jsonString)
            val dayWords = mutableListOf<ToeicWord>()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                if (jsonObject.getString("Day") == selectedDay) {
                    val word = jsonObject.getString("단어")
                    val meaning = jsonObject.getString("뜻")
                    dayWords.add(ToeicWord(selectedDay, word, meaning))
                }
            }

            while (questionsList.size < 10 && dayWords.isNotEmpty()) {
                val correctAnswerIndex = Random.nextInt(dayWords.size)
                val selectedWord = dayWords[correctAnswerIndex]

                if (usedWords.add(selectedWord.word)) {
                    val questionWord = selectedWord.meaning
                    val correctAnswer = selectedWord.word

                    val options = dayWords.filter { it.word != correctAnswer }
                        .shuffled().take(3).map { it.word }.toMutableList()
                    options.add(correctAnswer)
                    options.shuffle()

                    val correctOptionIndex = options.indexOf(correctAnswer) + 1

                    questionsList.add(
                        Question(
                            questionsList.size + 1,
                            "Find the correct English word:\n'$questionWord'",
                            options[0], options[1], options[2], options[3],
                            correctOptionIndex,
                            correctAnswer, // 추가된 필드
                            questionWord   // 추가된 필드
                        )
                    )

                    dayWords.removeAt(correctAnswerIndex)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Constants", "Error loading JSON", e)
        }

        return questionsList
    }
}