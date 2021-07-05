package com.rafo.egstask.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafo.egstask.R
import com.rafo.egstask.databinding.ProductionItemBinding
import com.rafo.egstask.util.load
import com.rafo.entities.responseentity.ProductionCompanies

class ProductionAdapter(private val prodList: List<ProductionCompanies>) :
    RecyclerView.Adapter<ProductionAdapter.ProductionHolder>() {

    class ProductionHolder(private val binding: ProductionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(production: ProductionCompanies) {
            binding.run {
                production.logoPath?.also { movieImage.load(it) }
                    ?: run { movieImage.setImageResource(R.drawable.ic_broken_image) }
                production.name?.let { movieName.text = it }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductionHolder {
        val binding =
            ProductionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductionHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductionHolder, position: Int) {
        holder.bind(prodList[position])
    }

    override fun getItemCount(): Int = prodList.size
}