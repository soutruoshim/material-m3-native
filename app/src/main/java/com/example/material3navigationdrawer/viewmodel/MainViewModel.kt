package com.example.material3navigationdrawer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.material3navigationdrawer.utils.SharedPrefManager

class MainViewModel(private val sharedPrefManager: SharedPrefManager) : ViewModel() {

    private val _language = MutableLiveData<String>()
    val language: LiveData<String> get() = _language

    fun loadLanguage() {
        _language.value = sharedPrefManager.getLanguage()
    }

    fun setLanguage(lang: String) {
        sharedPrefManager.saveLanguage(lang)
        _language.value = lang
    }
}