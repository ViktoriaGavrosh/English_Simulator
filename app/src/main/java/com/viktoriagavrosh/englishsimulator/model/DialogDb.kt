package com.viktoriagavrosh.englishsimulator.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Model represents a single dialog given from database
 *
 * @param id unique object identifier
 * @param question text of dialog question
 * @param shortAnswer text of dialog answer
 */
@Entity(tableName = "dialog")
data class DialogDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "question") val question: String = "",
    @ColumnInfo(name = "short_answer") val shortAnswer: String = "",
)
