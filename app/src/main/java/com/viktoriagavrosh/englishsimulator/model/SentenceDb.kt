package com.viktoriagavrosh.englishsimulator.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Model represents a single sentence given from database
 *
 * @param id unique object identifier
 * @param ruText sentence text in Russian
 * @param enText sentence text in English
 */
@Entity(tableName = "sentence")
data class SentenceDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "ru") val ruText: String,
    @ColumnInfo(name = "en") val enText: String,
)
