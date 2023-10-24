package com.akpdev.dailyvita.question

import com.google.gson.annotations.SerializedName

data class LastQuestion(
    @SerializedName("is_daily_exposure")
    val isDailyExposure: Boolean?,
    @SerializedName("is_somke")
    val isSmoke: Boolean?,
    val alcohol: String,
)
