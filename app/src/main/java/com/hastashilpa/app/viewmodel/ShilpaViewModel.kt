package com.hastashilpa.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hastashilpa.app.data.Material
import com.hastashilpa.app.data.MaterialRepository
import com.hastashilpa.app.data.Product
import kotlinx.coroutines.launch

class ShilpaViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = MaterialRepository(application)

    val allMaterials = repo.getAllMaterials()
    val allProducts = repo.getAllProducts()
    val listedProducts = repo.getListedProducts()

    fun addMaterial(name: String, quantity: Float, unit: String,
                    costPerUnit: Double, batchName: String) {
        viewModelScope.launch {
            repo.insertMaterial(
                Material(
                    name = name,
                    quantity = quantity,
                    unit = unit,
                    costPerUnit = costPerUnit,
                    batchName = batchName
                )
            )
        }
    }

    fun deleteMaterial(material: Material) {
        viewModelScope.launch { repo.deleteMaterial(material) }
    }

    fun addProduct(name: String, description: String, price: Double,
                   material: String, dimensions: String, hoursWorked: Float) {
        viewModelScope.launch {
            repo.insertProduct(
                Product(
                    name = name,
                    description = description,
                    price = price,
                    material = material,
                    dimensions = dimensions,
                    hoursWorked = hoursWorked
                )
            )
        }
    }

    fun listProduct(product: Product) {
        viewModelScope.launch {
            repo.updateProduct(product.copy(isListed = true))
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch { repo.deleteProduct(product) }
    }

    fun calculatePrice(
        materialCost: Double,
        hoursWorked: Float,
        hourlyRate: Double,
        profitMargin: Double
    ): Double {
        val baseCost = materialCost + (hoursWorked * hourlyRate)
        return baseCost + (baseCost * profitMargin / 100)
    }
}