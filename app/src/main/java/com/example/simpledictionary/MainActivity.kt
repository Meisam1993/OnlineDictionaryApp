package com.example.simpledictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpledictionary.adapter.MeaningAdapter
import com.example.simpledictionary.data.MeaningsItem
import com.example.simpledictionary.data.WordResponse
import com.example.simpledictionary.data.api.retrofit.RetrofitInstance
import com.example.simpledictionary.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter : MeaningAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.searchBtn.setOnClickListener {
            val word = binding.searchInputEt.text.toString()
            getMeaning(word)
            adapter = MeaningAdapter(emptyList())
            binding.rvMain.layoutManager = LinearLayoutManager(this)
             binding.rvMain.adapter = adapter
        }
    }

    private fun getMeaning(word: String) {
        progressBarVisibility(true)
        GlobalScope.launch {
            val response = RetrofitInstance.dictionaryApi.getMeaning(word)
            runOnUiThread {
                progressBarVisibility(false)
                response.body()?.first()?.let {
                     setUI(it)
                }
            }
        }
    }

    private fun progressBarVisibility(inProgress: Boolean) {
        if (inProgress) {
            binding.searchBtn.visibility = View.INVISIBLE
            binding.searchProgressBar.visibility = View.VISIBLE
        } else {
            binding.searchBtn.visibility = View.VISIBLE
            binding.searchProgressBar.visibility = View.GONE
        }
    }

    private fun setUI(response: WordResponse) {
        binding.wordSearched.text = response.word
        binding.phonetic.text = response.phonetics.toString()
        adapter.updateNewData(response.meanings as List<MeaningsItem>)
    }
}