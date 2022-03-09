package ru.ds.materialdesign.repository

import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.ds.materialdesign.repository.dto.mars.MarsPhotosServerResponseData


class RetrofitImpl {

    companion object{
        private const val BASE_URL = "https://api.nasa.gov/"
    }

    private val api by lazy {
        val retrofit = Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .build()
        retrofit.create(RetrofitApi::class.java)
    }

// Earth picture of the day
    fun getPictureOfTheDay():RetrofitApi{
        return api
    }
// Mars picture of the day
    fun getMarsPictureByDate(earth_date: String, apiKey: String, marsCallbackByDate: Callback<MarsPhotosServerResponseData>) {
        api.getMarsImageByDate(earth_date, apiKey).enqueue(marsCallbackByDate)
    }

}