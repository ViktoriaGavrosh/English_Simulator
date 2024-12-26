package com.viktoriagavrosh.englishsimulator.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Model represents a single word given from database
 *
 * @param id unique object identifier
 * @param englishWord word in Russian
 * @param russianWord word in English
 * @param theme theme of word
 */
@Entity(tableName = "word")
data class WordDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "en_word") val englishWord: String,
    @ColumnInfo(name = "ru_word") val russianWord: String,
    @ColumnInfo(name = "theme") val theme: String,
)

