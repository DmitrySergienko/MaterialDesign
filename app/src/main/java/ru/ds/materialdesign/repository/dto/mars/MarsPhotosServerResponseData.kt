package ru.ds.materialdesign.repository.dto.mars


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MarsPhotosServerResponseData(
    @SerializedName("photos")
    val photos: List<Photo>
)