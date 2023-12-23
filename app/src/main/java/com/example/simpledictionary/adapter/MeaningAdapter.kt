package com.example.simpledictionary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simpledictionary.data.MeaningsItem
import com.example.simpledictionary.databinding.ItemWordInformationBinding

class MeaningAdapter(private var meaningsItemList: List<MeaningsItem>) :
    RecyclerView.Adapter<MeaningAdapter.MeaningViewHolder>() {

    inner class MeaningViewHolder(private val binding: ItemWordInformationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindMeaning(meaningsItem: MeaningsItem) {
            binding.noun.text = meaningsItem.partOfSpeech
            binding.definitions.text = meaningsItem.definitions?.joinToString("\n\n") {
                var currentIndex = meaningsItem.definitions.indexOf(it)
                (currentIndex + 1).toString() + ". " + it?.definition.toString()
            }

            if (meaningsItem.synonyms?.isEmpty() == true) {
                binding.textView2.visibility = View.GONE
                 binding.synonyms.visibility = View.GONE
            } else {
                binding.textView2.visibility = View.VISIBLE
                binding.synonyms.visibility = View.VISIBLE
                binding.synonyms.text = meaningsItem.synonyms?.joinToString(", ")
            }

            if (meaningsItem.antonyms?.isEmpty() == true) {
                binding.textView3.visibility = View.GONE
                binding.antonym.visibility = View.GONE
            } else {
                binding.textView3.visibility = View.VISIBLE
                binding.antonym.visibility = View.VISIBLE
                binding.antonym.text = meaningsItem.antonyms?.joinToString(", ")
            }
        }
    }

    fun updateNewData(newMeaningItemList: List<MeaningsItem>) {
        meaningsItemList = newMeaningItemList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
        val binding =
            ItemWordInformationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MeaningViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return meaningsItemList.size
    }

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        holder.bindMeaning(meaningsItemList[position])
    }
}