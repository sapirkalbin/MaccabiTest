package com.axppress.maccabitest.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@androidx.room.Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    val thumbnail: String,
    val distinctNum: Int,
    val sum: Int,
)
