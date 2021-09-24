package com.timmy.rootcodingtest.viewmodel

import androidx.lifecycle.MutableLiveData
import com.timmy.rootcodingtest.api.ApiService
import com.timmy.rootcodingtest.database.NewsObj
import io.realm.Realm
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import retrofit2.Retrofit
import javax.inject.Inject

class MainRepository @Inject constructor() {

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var realm: Realm

    private val apiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    private val realmResults by lazy { realm.where(NewsObj::class.java).findAll() }

    private val result by lazy { MutableLiveData<MutableList<NewsObj>>() }

    fun getLiveDataInRealm() = result

    fun init(){ // 初始化，用於偵測realm資料庫是否有變化
        realmResults.addChangeListener { it ->
            MainScope().launch {
                result.value?.clear()
                result.postValue(it.asFlow().toList().toMutableList())
            }
        }
    }

    fun getDataFromAPI() {
        CoroutineScope(Dispatchers.IO).launch {
            val responseBody = apiService.getNews()
            MainScope().launch {
                realm.beginTransaction()

                responseBody.articles.forEach {
                    realm.createObject(NewsObj::class.java).apply {
                        photo = it.urlToImage ?: ""
                        title = it.title ?: ""
                    }
                }
                realm.commitTransaction()
            }
        }
    }



}
