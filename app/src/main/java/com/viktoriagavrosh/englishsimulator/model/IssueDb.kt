package com.viktoriagavrosh.englishsimulator.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Model represents a single issue given from database
 *
 * @param id unique object identifier
 * @param englishQuestion issue text in English
 * @param russianQuestion issue text in Russian
 * @param theme theme of issue
 */
@Entity(tableName = "issue")
data class IssueDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "text_en") val englishQuestion: String = "",
    @ColumnInfo(name = "text_ru") val russianQuestion: String = "",
    @ColumnInfo(name = "theme") val theme: String = "",
)
