package com.example.madcamp_androidproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_androidproject.databinding.ItemVocabularyBinding

class VocabularyAdapter(private val vocabularyList: List<VocabularyItem>)
    : RecyclerView.Adapter<VocabularyAdapter.VocabularyViewHolder>() {

    class VocabularyViewHolder(private val binding: ItemVocabularyBinding)
        : RecyclerView.ViewHolder(binding.root) {


        fun bind(item: VocabularyItem) {
            binding.tvDay.text = "${item.day}"
            binding.tvWord.text = "${item.word}"
            binding.tvMeaning.text = "${item.meaning}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocabularyViewHolder {
        val binding = ItemVocabularyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VocabularyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VocabularyViewHolder, position: Int) {
        holder.bind(vocabularyList[position])
    }

    override fun getItemCount(): Int {
        return vocabularyList.size
    }
}