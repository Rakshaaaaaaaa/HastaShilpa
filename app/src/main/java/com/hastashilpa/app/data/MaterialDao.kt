package com.hastashilpa.app.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MaterialDao {

    @Insert
    suspend fun insertMaterial(material: Material)

    @Delete
    suspend fun deleteMaterial(material: Material)

    @Query("SELECT * FROM materials ORDER BY timestamp DESC")
    fun getAllMaterials(): LiveData<List<Material>>

    @Query("SELECT SUM(quantity * costPerUnit) FROM materials")
    suspend fun getTotalMaterialCost(): Double?

    @Query("SELECT * FROM materials WHERE batchName = :batch")
    fun getMaterialsByBatch(batch: String): LiveData<List<Material>>
}