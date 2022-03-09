package ru.ds.materialdesign.repository.dto.mars


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MarsPhotosServerResponseData(
    @SerializedName("photos")
    val photos: List<Photo>
)