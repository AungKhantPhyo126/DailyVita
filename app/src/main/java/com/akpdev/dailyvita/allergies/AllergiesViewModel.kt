package com.akpdev.dailyvita.allergies

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akpdev.dailyvita.R
import com.akpdev.dailyvita.diet.Diet
import com.akpdev.dailyvita.diet.DietData
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AllergiesViewModel @Inject constructor(private val gson: Gson):ViewModel() {
    private val _allergiesLiveData = MutableLiveData<List<Allergy>>()
    val allergiesLiveData: LiveData<List<Allergy>>
        get() = _allergiesLiveData

    fun getAllergiesData(context: Context) {
        val rawResource = context.resources.openRawResource(R.raw.allergies)
        val jsonString = rawResource.bufferedReader().use { it.readText() }
        val dietData = gson.fromJson(jsonString, AllergiesData::class.java)
        if (_allergiesLiveData.value.isNullOrEmpty()){
            _allergiesLiveData.value = dietData.data.toList()
        }
    }

    fun checkChangeDietData(name:String){
        val list = _allergiesLiveData.value?.map { allergyData->
            if (allergyData.name == name){
                allergyData.copy(isSelected = !allergyData.isSelected)
            }else{
                allergyData
            }
        }.orEmpty()
        _allergiesLiveData.value = list
    }
}