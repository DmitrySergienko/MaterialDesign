package ru.ds.materialdesign.repository.epic


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class EarthEpicServerResponseData(
    @SerializedName("caption")
    val caption: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("identifier")
    val identifier: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("version")
    val version: String
)