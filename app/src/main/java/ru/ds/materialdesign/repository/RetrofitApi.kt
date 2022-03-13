package ru.ds.materialdesign.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.ds.materialdesign.repository.dto.mars.MarsPhotosServerResponseData
import ru.ds.materialdesign.repository.epic.EarthEpicServerResponseData


interface RetrofitApi {
    @GET("planetary/apod") // End point (part of URL-address without domen)
    fun getPictureOfTheDay(
            @Query("api_key") apiKey:String
    ): Call<PictureOfTheDayResponseData>


    @GET("planetary/apod")
    fun getPictureOfTheDay(
            @Query("api_key") apiKey:String,
            @Query("date") date:String
    ):Call<PictureOfTheDayResponseData>


    //   /mars-photos/api/v1/rovers/curiosity/photos     ?earth_date=2015-6-3&api_key=DEMO_KEY
    @GET("/mars-photos/api/v1/rovers/curiosity/photos")
    fun getMarsImageByDate(
            @Query("earth_date") earth_date: String,
            @Query("api_key") apiKey: String,
    ): Call<MarsPhotosServerResponseData>


    // Earth Polychromatic Imaging Camera
    @GET("EPIC/api/natural")
    fun getEPIC(
            @Query("api_key") apiKey: String,
    ): Call<List<EarthEpicServerResponseData>>

}