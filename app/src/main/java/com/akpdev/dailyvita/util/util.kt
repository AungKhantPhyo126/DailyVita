package com.akpdev.dailyvita.util

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.akpdev.dailyvita.databinding.CustomChipBinding
import com.google.android.material.chip.Chip


fun Context.createChip(label: String): Chip {
    val inflater: LayoutInflater = LayoutInflater.from(this)
    val chip = CustomChipBinding.inflate(inflater).root
    chip.text = label
    return chip
}

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    if (index1 in 0 until size && index2 in 0 until size) {
        val temp = this[index1]
        this[index1] = this[index2]
        this[index2] = temp
    }
}

fun AutoCompleteTextView.showDropdown(adapter: ArrayAdapter<String>?) {
    if (!TextUtils.isEmpty(this.text.toString())) {
        adapter?.filter?.filter(null)
    }
}