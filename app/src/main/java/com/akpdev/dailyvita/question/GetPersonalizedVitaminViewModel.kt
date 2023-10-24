package com.akpdev.dailyvita.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akpdev.dailyvita.healthConcerns.HealthConcerns

class GetPersonalizedVitaminViewModel:ViewModel() {
    private val _lastQuestionLiveData = MutableLiveData<LastQuestion>()
    val lastQuestionLiveData: LiveData<LastQuestion>
        get() = _lastQuestionLiveData

    fun answerLastQuestion(isDailyExposure:Boolean?,isSmoke:Boolean?,alcohol:String){
        _lastQuestionLiveData.value = LastQuestion(isDailyExposure, isSmoke, alcohol)
    }
}