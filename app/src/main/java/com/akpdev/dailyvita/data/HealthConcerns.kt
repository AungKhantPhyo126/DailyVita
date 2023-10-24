package com.akpdev.dailyvita.data

data class HealthConcernsData(
    val data:List<HealthConcerns>
)

data class HealthConcerns(
    val id:String,
    val name:String
)
