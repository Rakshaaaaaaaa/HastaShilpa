package com.hastashilpa.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hastashilpa.app.databinding.ActivityMainBinding
import com.hastashilpa.app.viewmodel.ShilpaViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val vm: ShilpaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm.allProducts.observe(this) { products ->
            binding.tvTotalProducts.text = "Total Products: ${products.size}"
        }

        vm.allMaterials.observe(this) { materials ->
            binding.tvTotalMaterials.text = "Total Materials Logged: ${materials.size}"
        }

        binding.btnDesignTrend.setOnClickListener {
            startActivity(Intent(this, DesignTrendActivity::class.java))
        }
        binding.btnBlueprint.setOnClickListener {
            startActivity(Intent(this, BlueprintActivity::class.java))
        }
        binding.btnMaterialTracker.setOnClickListener {
            startActivity(Intent(this, MaterialTrackerActivity::class.java))
        }
        binding.btnMarketplace.setOnClickListener {
            startActivity(Intent(this, MarketplaceActivity::class.java))
        }
        binding.btnPriceSuggester.setOnClickListener {
            startActivity(Intent(this, PriceSuggesterActivity::class.java))
        }
    }
}