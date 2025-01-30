package com.example.material3navigationdrawer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.material3navigationdrawer.utils.SharedPrefManager


class MainViewModelFactory(private val sharedPrefManager: SharedPrefManager) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(sharedPrefManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}