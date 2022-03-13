package ru.ds.materialdesign.repository.dto.mars


import com.google.gson.annotations.SerializedName

import androidx.annotation.Keep

@Keep
data class Camera(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("rover_id")
    val roverId: Int
)