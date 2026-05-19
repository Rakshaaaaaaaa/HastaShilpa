package com.hastashilpa.app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hastashilpa.app.databinding.ActivityPriceSuggesterBinding
import com.hastashilpa.app.viewmodel.ShilpaViewModel

class PriceSuggesterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPriceSuggesterBinding
    private val vm: ShilpaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPriceSuggesterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculatePrice.setOnClickListener {
            val materialCostStr = binding.etMaterialCost.text.toString()
            val hoursStr = binding.etHoursWorked.text.toString()
            val hourlyRateStr = binding.etHourlyRate.text.toString()
            val profitMarginStr = binding.etProfitMargin.text.toString()

            if (materialCostStr.isEmpty() || hoursStr.isEmpty()) {
                Toast.makeText(this,
                    "Please fill material cost and hours!",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val materialCost = materialCostStr.toDouble()
            val hours = hoursStr.toFloat()
            val hourlyRate = hourlyRateStr.toDoubleOrNull() ?: 50.0
            val profitMargin = profitMarginStr.toDoubleOrNull() ?: 30.0

            val suggestedPrice = vm.calculatePrice(
                materialCost, hours, hourlyRate, profitMargin
            )

            val laborCost = hours * hourlyRate
            val baseCost = materialCost + laborCost
            val profit = baseCost * profitMargin / 100

            binding.tvMaterialCostResult.text =
                "Material Cost: Rs ${"%.2f".format(materialCost)}"
            binding.tvLaborCostResult.text =
                "Labor Cost: Rs ${"%.2f".format(laborCost)}"
            binding.tvBaseCostResult.text =
                "Base Cost: Rs ${"%.2f".format(baseCost)}"
            binding.tvProfitResult.text =
                "Profit ($profitMargin%): Rs ${"%.2f".format(profit)}"
            binding.tvSuggestedPrice.text =
                "Suggested Price: Rs ${"%.2f".format(suggestedPrice)}"
            binding.tvMinPrice.text =
                "Minimum Price: Rs ${"%.2f".format(baseCost * 1.1)}"
            binding.tvMaxPrice.text =
                "Maximum Price: Rs ${"%.2f".format(suggestedPrice * 1.3)}"
        }
    }
}