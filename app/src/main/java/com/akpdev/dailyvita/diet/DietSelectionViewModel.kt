package com.akpdev.dailyvita.diet

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akpdev.dailyvita.R
import com.akpdev.dailyvita.healthConcerns.HealthConcerns
import com.akpdev.dailyvita.healthConcerns.HealthConcernsData
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DietSelectionViewModel @Inject constructor(private val gson: Gson):ViewModel() {
    private val _dietLiveData = MutableLiveData<List<Diet>>()
    val dietLiveData: LiveData<List<Diet>>
        get() = _dietLiveData

    fun getDietData(context: Context) {
        val rawResource = context.resources.openRawResource(R.raw.diets)
        val jsonString = rawResource.bufferedReader().use { it.readText() }
        val dietData = gson.fromJson(jsonString, DietData::class.java)
        if (_dietLiveData.value.isNullOrEmpty()){
            _dietLiveData.value = dietData.data.toList()
        }
    }

    fun checkChangeDietData(checkedId:String){
        val list = _dietLiveData.value?.map { dietdata->
            if (dietdata.id == checkedId){
                dietdata.copy(isSelected = !dietdata.isSelected)
            }else{
                dietdata
            }
        }.orEmpty()
        _dietLiveData.value = list
    }
}