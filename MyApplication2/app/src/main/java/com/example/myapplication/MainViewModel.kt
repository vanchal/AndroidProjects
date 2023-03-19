package com.example.myapplication

import android.view.View
import androidx.lifecycle.ViewModel

class MainViewModel(var initialCount : Int) : ViewModel() {
    var count : Int = initialCount

    fun increment(){
        count++;
    }
}