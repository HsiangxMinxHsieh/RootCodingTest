package com.timmy.rootcodingtest.database

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RealmInit() {

    @Singleton
    @Provides
    fun provideRealm(@ApplicationContext context: Context): Realm =
        try {
            Realm.init(context)
            Realm.getDefaultInstance()
        } catch (e: Exception) {
            Realm.getInstance(provideRealmConfig())
        }

    private fun provideRealmConfig(): RealmConfiguration = RealmConfiguration.Builder().name("news.realm").build()
}