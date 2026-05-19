package com.hastashilpa.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val price: Double,
    val material: String,
    val dimensions: String,
    val hoursWorked: Float,
    val isListed: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)