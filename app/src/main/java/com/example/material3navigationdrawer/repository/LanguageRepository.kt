package com.example.material3navigationdrawer.repository

import com.example.material3navigationdrawer.model.LanguageModel

class LanguageRepository {
    fun getLanguages(): List<LanguageModel> {
        return listOf(
            LanguageModel("en", "English"),
            LanguageModel("ar", "العربية"),
            LanguageModel("km", "ភាសាខ្មែរ")
        )
    }
}