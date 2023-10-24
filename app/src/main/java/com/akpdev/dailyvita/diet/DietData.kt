package com.akpdev.dailyvita.diet

import com.google.gson.annotations.SerializedName

data class DietData(
    val data:List<Diet>
)
data class Diet(
    val id:String,
    val name:String,
    @SerializedName("tool_tip")
    val toolTip:String,
    val isSelected:Boolean
)
