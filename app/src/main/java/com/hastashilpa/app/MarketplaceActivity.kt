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
import com.hastashilpa.app.data.Product
import com.hastashilpa.app.databinding.ActivityMarketplaceBinding
import com.hastashilpa.app.viewmodel.ShilpaViewModel

class MarketplaceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMarketplaceBinding
    private val vm: ShilpaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketplaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMarketplace.layoutManager = LinearLayoutManager(this)

        vm.allProducts.observe(this) { products ->
            if (products.isEmpty()) {
                binding.tvNoProducts.visibility = View.VISIBLE
                binding.rvMarketplace.visibility = View.GONE
                binding.tvListedCount.text = "Listed: 0 products"
            } else {
                binding.tvNoProducts.visibility = View.GONE
                binding.rvMarketplace.visibility = View.VISIBLE
                val listed = products.count { it.isListed }
                binding.tvListedCount.text = "Listed: $listed / ${products.size} products"
                binding.rvMarketplace.adapter = ProductAdapter(
                    products,
                    onList = { product ->
                        vm.listProduct(product)
                        Toast.makeText(this,
                            "${product.name} listed!", Toast.LENGTH_SHORT).show()
                    },
                    onDelete = { product ->
                        vm.deleteProduct(product)
                        Toast.makeText(this,
                            "Product deleted!", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }

        binding.btnAddProduct.setOnClickListener {
            val name = binding.etProductName.text.toString()
            val desc = binding.etProductDesc.text.toString()
            val priceStr = binding.etProductPrice.text.toString()
            val material = binding.etProductMaterial.text.toString()
            val dimensions = binding.etProductDimensions.text.toString()
            val hoursStr = binding.etHoursWorked.text.toString()

            if (name.isEmpty() || priceStr.isEmpty()) {
                Toast.makeText(this,
                    "Please fill name and price!",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            vm.addProduct(
                name, desc, priceStr.toDouble(),
                material, dimensions,
                hoursStr.toFloatOrNull() ?: 0f
            )

            binding.etProductName.text?.clear()
            binding.etProductDesc.text?.clear()
            binding.etProductPrice.text?.clear()
            binding.etProductMaterial.text?.clear()
            binding.etProductDimensions.text?.clear()
            binding.etHoursWorked.text?.clear()

            Toast.makeText(this, "Product added!", Toast.LENGTH_SHORT).show()
        }
    }

    class ProductAdapter(
        private val products: List<Product>,
        private val onList: (Product) -> Unit,
        private val onDelete: (Product) -> Unit
    ) : RecyclerView.Adapter<ProductAdapter.VH>() {

        inner class VH(view: View) : RecyclerView.ViewHolder(view) {
            val tvName: TextView = view.findViewById(R.id.tvProductName)
            val tvDesc: TextView = view.findViewById(R.id.tvProductDesc)
            val tvPrice: TextView = view.findViewById(R.id.tvProductPrice)
            val tvMaterial: TextView = view.findViewById(R.id.tvProductMaterial)
            val tvDimensions: TextView = view.findViewById(R.id.tvProductDimensions)
            val tvStatus: TextView = view.findViewById(R.id.tvProductStatus)
            val tvList: TextView = view.findViewById(R.id.tvListProduct)
            val tvDelete: TextView = view.findViewById(R.id.tvDeleteProduct)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            VH(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product, parent, false))

        override fun onBindViewHolder(holder: VH, position: Int) {
            val p = products[position]
            holder.tvName.text = p.name
            holder.tvDesc.text = p.description
            holder.tvPrice.text = "Rs ${"%.2f".format(p.price)}"
            holder.tvMaterial.text = "Material: ${p.material}"
            holder.tvDimensions.text = "Size: ${p.dimensions}"
            holder.tvStatus.text = if (p.isListed) "Listed" else "Not Listed"
            holder.tvStatus.setTextColor(
                if (p.isListed)
                    android.graphics.Color.parseColor("#2E7D32")
                else
                    android.graphics.Color.parseColor("#888888")
            )
            holder.tvList.text = if (p.isListed) "Listed" else "List Now"
            holder.tvList.setOnClickListener { if (!p.isListed) onList(p) }
            holder.tvDelete.setOnClickListener { onDelete(p) }
        }

        override fun getItemCount() = products.size
    }
}