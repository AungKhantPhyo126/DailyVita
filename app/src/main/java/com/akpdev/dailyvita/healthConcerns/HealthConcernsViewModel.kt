package com.akpdev.dailyvita.healthConcerns

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akpdev.dailyvita.R
import com.akpdev.dailyvita.data.HealthConcerns
import com.akpdev.dailyvita.data.HealthConcernsData
import com.google.gson.Gson

class HealthConcernsViewModel:ViewModel() {
    private val _healthConcernsData = MutableLiveData<List<HealthConcerns>>()
    val healthConcerns : LiveData<List<HealthConcerns>>
        get() = _healthConcernsData

    fun getHealthConcernsData(context:Context){
        val rawResource = context.resources.openRawResource(R.raw.healthconcern)
        val jsonString = rawResource.bufferedReader().use { it.readText() }
        val gson = Gson()
        val myData = gson.fromJson(jsonString, HealthConcernsData::class.java)

        _healthConcernsData.value = myData.data.toList()
    }
}