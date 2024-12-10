package com.viktoriagavrosh.englishsimulator.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Issue(
    val id: Int = 0,
    val englishQuestion: String = "",
    val russianQuestion: String = "",
    val theme: String = "",
)

