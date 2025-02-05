package com.viktoriagavrosh.englishsimulator.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Model represents a single statistic object given from database
 *
 * @param id unique object identifier
 * @param date day
 * @param translateScore score of quest "Translate sentences"
 * @param issueScore score of quest "Tell about yourself"
 * @param dialogScore score of quest "Short dialogs"
 * @param wordScore score of quest "FlashCards"
 */
@Entity(tableName = "statistic")
data class StatisticDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "translate_score") val translateScore: Int,
    @ColumnInfo(name = "issue_score") val issueScore: Int,
    @ColumnInfo(name = "dialog_score") val dialogScore: Int,
    @ColumnInfo(name = "word_score") val wordScore: Int,
)
