package com.akpdev.dailyvita.diet

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akpdev.dailyvita.databinding.ItemDietBinding

class DietRecyclerAdapter(
    private val showToolTips:(toolTips:String,view:ImageView)->Unit,
    private val checkChange:(id:String)->Unit,
):
    ListAdapter<Diet, DietRecyclerAdapter.DietViewHolder>(
    DietDiffUtil
) {

    inner class DietViewHolder(
        private val binding: ItemDietBinding
    ): RecyclerView.ViewHolder(binding.root){
        private var _data:Diet? = null
        val data:Diet
            get() = _data!!
        init {
            binding.ivToolTips.setOnClickListener {
                showToolTips(data.toolTip,binding.ivToolTips)
            }
            binding.radioBtn.setOnClickListener {
                checkChange(data.id)
            }
        }
        fun bind(data: Diet){
            _data = data
            binding.radioBtn.text = data.name
            binding.radioBtn.isChecked = data.isSelected
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietViewHolder {
        return DietViewHolder(ItemDietBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DietViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object DietDiffUtil : DiffUtil.ItemCallback<Diet>() {
    override fun areItemsTheSame(oldItem: Diet, newItem: Diet): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Diet,
        newItem: Diet
    ): Boolean {
        return oldItem == newItem
    }

}