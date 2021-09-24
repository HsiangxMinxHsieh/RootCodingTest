package com.timmy.rootcodingtest.database

import io.realm.RealmObject

open class NewsObj : RealmObject() {
    var photo: String = ""
    var title: String = ""
}