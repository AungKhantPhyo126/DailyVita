package com.akpdev.dailyvita.healthConcerns

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akpdev.dailyvita.R
import com.akpdev.dailyvita.util.swap
import com.google.gson.Gson

class HealthConcernsViewModel : ViewModel() {
    private val _healthConcernsData = MutableLiveData<List<HealthConcerns>>()
    val healthConcerns: LiveData<List<HealthConcerns>>
        get() = _healthConcernsData

    fun getHealthConcernsData(context: Context) {
        val rawResource = context.resources.openRawResource(R.raw.healthconcern)
        val jsonString = rawResource.bufferedReader().use { it.readText() }
        val gson = Gson()
        val myData = gson.fromJson(jsonString, HealthConcernsData::class.java)
        if (_healthConcernsData.value.isNullOrEmpty()){
            _healthConcernsData.value = myData.data.toList()
        }
    }

    private val _prioritizeData = MutableLiveData<MutableList<HealthConcerns>>()
    val prioritizeData: LiveData<MutableList<HealthConcerns>>
        get() = _prioritizeData

    fun addPrioritizeData(itemIds: List<String>) {
        val filteredList = _healthConcernsData.value?.filter { it.id in itemIds }

        _prioritizeData.value = filteredList?.map { it.copy(isSelected = true) }?.toMutableList()
        val updatedHealthConcernsData = _healthConcernsData.value?.map { item ->
            if (item.id in itemIds) {
                item.copy(isSelected = true)
            } else {
                item
            }
        }
        _healthConcernsData.value = updatedHealthConcernsData.orEmpty()
    }

    fun swapPosition(draggedItemId: Int, targetItemId: Int) {
        _prioritizeData.value = _prioritizeData.value?.apply { swap(draggedItemId, targetItemId) }
    }
}