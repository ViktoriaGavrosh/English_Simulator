package com.viktoriagavrosh.englishsimulator.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single Sentence from database
 */
@Entity(tableName = "sentence")
internal data class SentenceDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "ru") val ruText: String,
    @ColumnInfo(name = "en") val enText: String,
)
