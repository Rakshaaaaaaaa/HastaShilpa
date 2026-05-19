package com.hastashilpa.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hastashilpa.app.data.Material
import com.hastashilpa.app.databinding.ActivityMaterialTrackerBinding
import com.hastashilpa.app.viewmodel.ShilpaViewModel

class MaterialTrackerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMaterialTrackerBinding
    private val vm: ShilpaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaterialTrackerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMaterials.layoutManager = LinearLayoutManager(this)

        vm.allMaterials.observe(this) { materials ->
            if (materials.isEmpty()) {
                binding.tvNoMaterials.visibility = View.VISIBLE
                binding.rvMaterials.visibility = View.GONE
                binding.tvTotalCost.text = "Total Material Cost: Rs 0.00"
            } else {
                binding.tvNoMaterials.visibility = View.GONE
                binding.rvMaterials.visibility = View.VISIBLE
                val totalCost = materials.sumOf { it.quantity * it.costPerUnit }
                binding.tvTotalCost.text =
                    "Total Material Cost: Rs ${"%.2f".format(totalCost)}"
                binding.rvMaterials.adapter = MaterialAdapter(materials) { material ->
                    vm.deleteMaterial(material)
                    Toast.makeText(this,
                        "Material deleted!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnAddMaterial.setOnClickListener {
            val name = binding.etMaterialName.text.toString()
            val qtyStr = binding.etMaterialQty.text.toString()
            val unit = binding.etMaterialUnit.text.toString()
            val costStr = binding.etMaterialCost.text.toString()
            val batch = binding.etBatchName.text.toString()

            if (name.isEmpty() || qtyStr.isEmpty() || costStr.isEmpty()) {
                Toast.makeText(this,
                    "Please fill name, quantity and cost!",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            vm.addMaterial(
                name, qtyStr.toFloat(),
                unit.ifEmpty { "pieces" },
                costStr.toDouble(), batch
            )

            binding.etMaterialName.text?.clear()
            binding.etMaterialQty.text?.clear()
            binding.etMaterialUnit.text?.clear()
            binding.etMaterialCost.text?.clear()
            binding.etBatchName.text?.clear()

            Toast.makeText(this, "Material logged!", Toast.LENGTH_SHORT).show()
        }
    }

    class MaterialAdapter(
        private val materials: List<Material>,
        private val onDelete: (Material) -> Unit
    ) : RecyclerView.Adapter<MaterialAdapter.VH>() {

        inner class VH(view: View) : RecyclerView.ViewHolder(view) {
            val tvName: TextView = view.findViewById(R.id.tvMaterialName)
            val tvQty: TextView = view.findViewById(R.id.tvMaterialQty)
            val tvCost: TextView = view.findViewById(R.id.tvMaterialCost)
            val tvBatch: TextView = view.findViewById(R.id.tvMaterialBatch)
            val tvDelete: TextView = view.findViewById(R.id.tvDeleteMaterial)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            VH(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_material, parent, false))

        override fun onBindViewHolder(holder: VH, position: Int) {
            val m = materials[position]
            holder.tvName.text = m.name
            holder.tvQty.text = "${m.quantity} ${m.unit}"
            holder.tvCost.text = "Rs ${"%.2f".format(m.quantity * m.costPerUnit)}"
            holder.tvBatch.text = if (m.batchName.isEmpty()) "No batch" else m.batchName
            holder.tvDelete.setOnClickListener { onDelete(m) }
        }

        override fun getItemCount() = materials.size
    }
}