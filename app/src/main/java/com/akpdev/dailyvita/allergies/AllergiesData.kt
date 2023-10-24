package com.akpdev.dailyvita.allergies

data class AllergiesData(
    val data:List<Allergy>
)

data class Allergy(
    val id:String,
    val name:String,
    val isSelected:Boolean
)
