package com.example.simpledictionary.data.api

import com.example.simpledictionary.data.WordResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("en/{word}")
    suspend fun getMeaning(@Path("word") word: String): Response<List<WordResponse>>
}