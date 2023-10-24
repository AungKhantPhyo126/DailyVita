package com.akpdev.dailyvita.healthConcerns

data class HealthConcernsData(
    val data:List<HealthConcerns>
)

data class HealthConcerns(
    val id:String,
    val name:String,
    val isSelected:Boolean
)
