package ru.ds.materialdesign.repository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PictureOfTheDayRemoteImp {

    private val api by lazy {
        val retrofit = Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .build()
        retrofit.create(PictureOfTheDayAPI::class.java)
    }

    private val baseUrl = "https://api.nasa.gov/"
    fun getRetrofitImpl():PictureOfTheDayAPI{
        return api
    }

}