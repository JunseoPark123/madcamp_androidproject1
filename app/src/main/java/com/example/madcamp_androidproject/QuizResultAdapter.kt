package com.example.madcamp_androidproject

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuizResultAdapter(private val quizResults: List<QuizResult>) : RecyclerView.Adapter<QuizResultAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvWord: TextView = view.findViewById(R.id.tv_word)
        val tvMeaning: TextView = view.findViewById(R.id.tv_meaning)
        val tvIsCorrect: TextView = view.findViewById(R.id.tv_is_correct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quiz_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = quizResults[position]
        holder.tvWord.text = result.word
        holder.tvMeaning.text = result.meaning
        holder.tvMeaning.setTypeface(null, Typeface.BOLD)
        holder.tvIsCorrect.apply {
            setTypeface(null, Typeface.BOLD)
            setTextColor(Color.RED)
            text = if (result.isCorrect) "O" else "X"
        }
    }

    override fun getItemCount() = quizResults.size
}