package com.timmy.rootcodingtest.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel


class NewsViewModel @ViewModelInject constructor(private val repository: NewsRepository) : ViewModel() {

    fun getData() {
        repository.init()
        repository.getDataFromAPI()
    }

    fun getLiveDataInRealm() = repository.getLiveDataInRealm()

}