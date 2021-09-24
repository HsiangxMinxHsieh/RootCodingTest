package com.timmy.rootcodingtest.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel


class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) : ViewModel() {

    fun getData() {
        mainRepository.init()
        mainRepository.getDataFromAPI()
    }

    fun getLiveDataInRealm() = mainRepository.getLiveDataInRealm()

}