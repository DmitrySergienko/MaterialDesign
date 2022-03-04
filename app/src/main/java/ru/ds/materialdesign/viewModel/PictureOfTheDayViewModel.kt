package ru.ds.materialdesign.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.ds.materialdesign.BuildConfig
import ru.ds.materialdesign.repository.PictureOfTheDayRemoteImp
import ru.ds.materialdesign.repository.PictureOfTheDayResponseData

class PictureOfTheDayViewModel(
    private val liveData: MutableLiveData<PictureOfTheDayState> = MutableLiveData(),
    private val retrofitImpl: PictureOfTheDayRemoteImp = PictureOfTheDayRemoteImp()
): ViewModel() {

    fun getLiveData():LiveData<PictureOfTheDayState>{
        return liveData
    }
    fun sendServerRequest() {
        liveData.value = PictureOfTheDayState.Loading(0)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            liveData.value = PictureOfTheDayState.Error(Throwable("wrong key"))
        } else {
            retrofitImpl.getRetrofitImpl().getPictureOfTheDay(apiKey).enqueue(callback)
        }
    }

    fun sendServerRequest(date:String) {
        liveData.value = PictureOfTheDayState.Loading(0)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            liveData.value = PictureOfTheDayState.Error(Throwable("wrong key"))
        } else {
            retrofitImpl.getRetrofitImpl().getPictureOfTheDay(apiKey,date).enqueue(callback)
        }
    }
    private val callback = object : Callback<PictureOfTheDayResponseData>{
        override fun onResponse(
                call: Call<PictureOfTheDayResponseData>,
                response: Response<PictureOfTheDayResponseData>
        ) {
            if(response.isSuccessful&&response.body()!=null){
                liveData.value = PictureOfTheDayState.Success(response.body()!!)
            }else{
                liveData.value = PictureOfTheDayState.Error(IllegalStateException("Ошибка"))
            }
        }

        //https://material.io/components/bottom-navigation/android#theming-a-bottom-navigation-bar
        override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable) {
            liveData.value = PictureOfTheDayState.Error(IllegalStateException("onFailure"))
        }

    }

}