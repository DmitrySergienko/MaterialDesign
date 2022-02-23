package ru.ds.materialdesign.repository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PictureOfTheDayRemoteImp {

    private val baseURL = "https://api.nasa.gov/"

    fun getRetrofitImp():PictureOfTheDayAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        return retrofit.create(PictureOfTheDayAPI::class.java)
    }
}