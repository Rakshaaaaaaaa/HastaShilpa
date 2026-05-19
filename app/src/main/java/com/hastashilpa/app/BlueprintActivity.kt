package com.hastashilpa.app

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.hastashilpa.app.databinding.ActivityBlueprintBinding

class BlueprintActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBlueprintBinding

    data class Blueprint(
        val name: String,
        val dimensions: String,
        val materials: String,
        val steps: String,
        val tips: String,
        val estimatedTime: String,
        val estimatedCost: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlueprintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val blueprints = listOf(
            "Bamboo Laptop Stand",
            "Cane Lampshade",
            "Bamboo Phone Holder",
            "Cane Storage Basket",
            "Bamboo Wall Art",
            "Bamboo Pen Stand",
            "Cane Fruit Bowl",
            "Bamboo Key Holder"
        )

        binding.spinnerBlueprint.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, blueprints)

        binding.btnViewBlueprint.setOnClickListener {
            val selected = binding.spinnerBlueprint.selectedItem.toString()
            val blueprint = getBlueprint(selected)
            showBlueprint(blueprint)
        }
    }

    private fun showBlueprint(bp: Blueprint) {
        binding.tvBlueprintName.text = bp.name
        binding.tvDimensions.text = "Dimensions: ${bp.dimensions}"
        binding.tvBlueprintMaterials.text = "Materials Needed:\n${bp.materials}"
        binding.tvSteps.text = "Steps:\n${bp.steps}"
        binding.tvBlueprintTips.text = "Tips:\n${bp.tips}"
        binding.tvEstimatedTime.text = "Time: ${bp.estimatedTime}"
        binding.tvEstimatedCost.text = "Est. Cost: ${bp.estimatedCost}"
    }

    private fun getBlueprint(name: String): Blueprint {
        return when (name) {
            "Bamboo Laptop Stand" -> Blueprint(
                name = "Bamboo Laptop Stand",
                dimensions = "30cm x 25cm x 15cm",
                materials = "• 4 bamboo poles (30cm each)\n• 2 bamboo poles (25cm each)\n• Bamboo rope\n• Sandpaper",
                steps = "1. Cut bamboo poles to size\n2. Sand all pieces smooth\n3. Arrange in angled frame\n4. Tie with bamboo rope\n5. Apply varnish\n6. Let dry for 24 hours",
                tips = "• Use dry bamboo poles\n• Sand well to avoid splinters\n• Apply 2 coats of varnish",
                estimatedTime = "3-4 hours",
                estimatedCost = "Rs 150-200"
            )
            "Cane Lampshade" -> Blueprint(
                name = "Cane Lampshade",
                dimensions = "25cm diameter x 20cm height",
                materials = "• Cane strips (50 pieces)\n• Wire frame\n• Binding wire\n• Varnish",
                steps = "1. Shape wire frame\n2. Soak cane strips\n3. Weave cane on frame\n4. Secure ends\n5. Apply varnish\n6. Attach to lamp fitting",
                tips = "• Soak cane for 30 mins\n• Weave tightly\n• Keep consistent spacing",
                estimatedTime = "5-6 hours",
                estimatedCost = "Rs 200-250"
            )
            "Bamboo Phone Holder" -> Blueprint(
                name = "Bamboo Phone Holder",
                dimensions = "10cm x 8cm x 12cm",
                materials = "• 1 thick bamboo pole\n• Sandpaper\n• Varnish",
                steps = "1. Cut bamboo to 12cm\n2. Cut slot for phone\n3. Sand all edges smooth\n4. Apply varnish\n5. Let dry completely",
                tips = "• Cut slot at 70 degree angle\n• Width should fit phone\n• Multiple slots for more phones",
                estimatedTime = "1-2 hours",
                estimatedCost = "Rs 30-50"
            )
            "Cane Storage Basket" -> Blueprint(
                name = "Cane Storage Basket",
                dimensions = "20cm x 20cm x 15cm",
                materials = "• Thick cane strips\n• Thin cane strips\n• Basket base\n• Varnish",
                steps = "1. Prepare base with thick cane\n2. Soak thin cane strips\n3. Weave sides up\n4. Fold and secure top edge\n5. Apply varnish",
                tips = "• Keep tension consistent\n• Soak cane well\n• Use sturdy base",
                estimatedTime = "4-5 hours",
                estimatedCost = "Rs 100-150"
            )
            else -> Blueprint(
                name = name,
                dimensions = "Custom dimensions",
                materials = "• Bamboo/Cane as needed\n• Rope or wire\n• Sandpaper\n• Varnish",
                steps = "1. Plan design\n2. Gather materials\n3. Cut to size\n4. Assemble\n5. Finish with varnish",
                tips = "• Plan before cutting\n• Measure twice cut once\n• Sand all edges",
                estimatedTime = "2-4 hours",
                estimatedCost = "Rs 100-300"
            )
        }
    }
}