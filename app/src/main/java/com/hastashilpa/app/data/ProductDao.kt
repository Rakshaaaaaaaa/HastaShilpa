package com.hastashilpa.app.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {

    @Insert
    suspend fun insertProduct(product: Product): Long

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("SELECT * FROM products ORDER BY timestamp DESC")
    fun getAllProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM products WHERE isListed = 1")
    fun getListedProducts(): LiveData<List<Product>>
}