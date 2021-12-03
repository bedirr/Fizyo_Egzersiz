package com.mucahit_bedir.fizyoegzersiz.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    private val _bottomNavVisibilityLiveData = MutableLiveData<Boolean>()
    val bottomNavVisibilityLiveData: LiveData<Boolean> = _bottomNavVisibilityLiveData

    fun setBottomNavVisibility(visibility : Boolean){
        _bottomNavVisibilityLiveData.postValue(visibility)
    }

}