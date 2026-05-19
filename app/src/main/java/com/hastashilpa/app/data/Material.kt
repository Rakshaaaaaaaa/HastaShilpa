package com.hastashilpa.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materials")
data class Material(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val quantity: Float,
    val unit: String,
    val costPerUnit: Double,
    val batchName: String = "",
    val timestamp: Long = System.currentTimeMillis()
)