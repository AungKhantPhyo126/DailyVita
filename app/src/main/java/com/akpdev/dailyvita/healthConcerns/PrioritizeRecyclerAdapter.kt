package com.akpdev.dailyvita.healthConcerns

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akpdev.dailyvita.databinding.ItemPrioritizeBinding

class PrioritizeRecyclerAdapter:ListAdapter<HealthConcerns,PrioritizeRecyclerAdapter.PrioritizeViewHolder>(
    PrioritizeDiffUtil
) {

    inner class PrioritizeViewHolder(
        private val binding:ItemPrioritizeBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(data:HealthConcerns){
            binding.tvName.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrioritizeViewHolder {
        return PrioritizeViewHolder(ItemPrioritizeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PrioritizeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object PrioritizeDiffUtil : DiffUtil.ItemCallback<HealthConcerns>() {
    override fun areItemsTheSame(oldItem: HealthConcerns, newItem: HealthConcerns): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: HealthConcerns,
        newItem: HealthConcerns
    ): Boolean {
        return oldItem == newItem
    }

}