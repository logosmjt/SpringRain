package com.jingtian.springrain.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "operation")
data class Operation(
    @PrimaryKey @ColumnInfo(name = "id") val operationId: String,
    val type: Int,
    @ColumnInfo(name = "image_urls")
    val imageUrls: String = ""
)