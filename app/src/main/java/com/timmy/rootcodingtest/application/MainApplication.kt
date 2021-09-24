package com.timmy.rootcodingtest.application

import android.app.Application
import com.timmy.rootcodingtest.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        Timber.plant(Timber.DebugTree())

    }

}