package com.example.material3navigationdrawer.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun saveLanguage(lang: String) {
        prefs.edit().putString("language", lang).apply()
    }

    fun getLanguage(): String {
        return prefs.getString("language", "en") ?: "en"
    }
}