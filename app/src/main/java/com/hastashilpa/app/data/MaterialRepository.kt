package com.hastashilpa.app.data

import android.content.Context

class MaterialRepository(context: Context) {
    private val db = AppDatabase.getDatabase(context)
    private val materialDao = db.materialDao()
    private val productDao = db.productDao()

    fun getAllMaterials() = materialDao.getAllMaterials()
    fun getAllProducts() = productDao.getAllProducts()
    fun getListedProducts() = productDao.getListedProducts()

    suspend fun insertMaterial(material: Material) = materialDao.insertMaterial(material)
    suspend fun deleteMaterial(material: Material) = materialDao.deleteMaterial(material)
    suspend fun insertProduct(product: Product) = productDao.insertProduct(product)
    suspend fun updateProduct(product: Product) = productDao.updateProduct(product)
    suspend fun deleteProduct(product: Product) = productDao.deleteProduct(product)
    suspend fun getTotalMaterialCost() = materialDao.getTotalMaterialCost()
}