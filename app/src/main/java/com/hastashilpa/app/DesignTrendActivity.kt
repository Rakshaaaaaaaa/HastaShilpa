package com.hastashilpa.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hastashilpa.app.databinding.ActivityDesignTrendBinding

class DesignTrendActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDesignTrendBinding

    data class DesignTrend(
        val title: String,
        val description: String,
        val material: String,
        val marketPrice: String,
        val difficulty: String,
        val trend: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDesignTrendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val trends = listOf(
            DesignTrend(
                "Bamboo Laptop Stand",
                "Modern ergonomic laptop stand made from bamboo poles",
                "Bamboo", "₹800-1200", "Medium", "🔥 Trending"
            ),
            DesignTrend(
                "Cane Lampshade",
                "Geometric cane lampshade for home decor",
                "Cane", "₹600-900", "Medium", "🔥 Trending"
            ),
            DesignTrend(
                "Bamboo Phone Holder",
                "Minimalist bamboo phone stand for desk",
                "Bamboo", "₹200-400", "Easy", "⭐ Popular"
            ),
            DesignTrend(
                "Cane Storage Basket",
                "Modern geometric storage basket",
                "Cane", "₹400-700", "Easy", "⭐ Popular"
            ),
            DesignTrend(
                "Bamboo Wall Art",
                "Abstract bamboo wall decoration panels",
                "Bamboo", "₹1000-2000", "Hard", "🌟 Premium"
            ),
            DesignTrend(
                "Cane Magazine Rack",
                "Stylish cane magazine and book holder",
                "Cane", "₹500-800", "Medium", "⭐ Popular"
            ),
            DesignTrend(
                "Bamboo Pen Stand",
                "Eco-friendly bamboo pen and stationery holder",
                "Bamboo", "₹150-300", "Easy", "🔥 Trending"
            ),
            DesignTrend(
                "Cane Fruit Bowl",
                "Modern woven cane fruit bowl for kitchen",
                "Cane", "₹300-500", "Medium", "⭐ Popular"
            ),
            DesignTrend(
                "Bamboo Key Holder",
                "Wall mounted bamboo key organizer",
                "Bamboo", "₹200-350", "Easy", "🔥 Trending"
            ),
            DesignTrend(
                "Cane Room Divider",
                "Large decorative cane room partition",
                "Cane", "₹2000-4000", "Hard", "🌟 Premium"
            )
        )

        binding.rvDesignTrends.layoutManager = LinearLayoutManager(this)
        binding.rvDesignTrends.adapter = TrendAdapter(trends)
        binding.tvTrendCount.text = "${trends.size} trending designs available"
    }

    class TrendAdapter(private val trends: List<DesignTrend>) :
        RecyclerView.Adapter<TrendAdapter.VH>() {

        inner class VH(view: View) : RecyclerView.ViewHolder(view) {
            val tvTitle: TextView = view.findViewById(R.id.tvDesignTitle)
            val tvDesc: TextView = view.findViewById(R.id.tvDesignDesc)
            val tvMaterial: TextView = view.findViewById(R.id.tvDesignMaterial)
            val tvPrice: TextView = view.findViewById(R.id.tvDesignPrice)
            val tvDifficulty: TextView = view.findViewById(R.id.tvDesignDifficulty)
            val tvTrend: TextView = view.findViewById(R.id.tvDesignTrend)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            VH(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_design, parent, false))

        override fun onBindViewHolder(holder: VH, position: Int) {
            val t = trends[position]
            holder.tvTitle.text = t.title
            holder.tvDesc.text = t.description
            holder.tvMaterial.text = "Material: ${t.material}"
            holder.tvPrice.text = "Market Price: ${t.marketPrice}"
            holder.tvDifficulty.text = "Difficulty: ${t.difficulty}"
            holder.tvTrend.text = t.trend
            holder.tvTrend.setTextColor(
                when {
                    t.trend.contains("Trending") ->
                        android.graphics.Color.parseColor("#B71C1C")
                    t.trend.contains("Premium") ->
                        android.graphics.Color.parseColor("#F57F17")
                    else ->
                        android.graphics.Color.parseColor("#1B5E20")
                }
            )
        }

        override fun getItemCount() = trends.size
    }
}