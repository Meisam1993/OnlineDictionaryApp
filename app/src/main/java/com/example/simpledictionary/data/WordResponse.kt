package com.example.simpledictionary.data

data class WordResponse(
    val phonetics: List<PhoneticsItem?>? = null,
    val word: String? = null,
    val meanings: List<MeaningsItem?>? = null,
)

data class PhoneticsItem(
    val text: String? = null
)

data class MeaningsItem(
    val synonyms: List<String?>? = null,
    val partOfSpeech: String? = null,
    val antonyms: List<Any?>? = null,
    val definitions: List<DefinitionsItem?>? = null
)

data class DefinitionsItem(
    val definition: String? = null
)

