package ru.ds.materialdesign.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.ds.materialdesign.BuildConfig
import ru.ds.materialdesign.repository.RetrofitImpl
import ru.ds.materialdesign.repository.PictureOfTheDayResponseData

class PictureOfTheDayViewModel(
        private val liveData: MutableLiveData<AppState> = MutableLiveData(),
        private val retrofitImpl: RetrofitImpl = RetrofitImpl()
): ViewModel() {

    fun getLiveData():LiveData<AppState>{
        return liveData
    }
    fun sendServerRequest() {
        liveData.postValue(AppState.Loading)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            liveData.value = AppState.Error(Throwable("wrong key"))
        } else {
            retrofitImpl.getPictureOfTheDay().getPictureOfTheDay(apiKey).enqueue(callback)
        }
    }

    fun sendServerRequest(date:String) {
        liveData.postValue(AppState.Loading)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            liveData.value = AppState.Error(Throwable("wrong key"))
        } else {
            retrofitImpl.getPictureOfTheDay().getPictureOfTheDay(apiKey,date).enqueue(callback)
        }
    }
    private val callback = object : Callback<PictureOfTheDayResponseData>{
        override fun onResponse(
                call: Call<PictureOfTheDayResponseData>,
                response: Response<PictureOfTheDayResponseData>
        ) {
            if(response.isSuccessful&&response.body()!=null){
                liveData.value = AppState.Success(response.body()!!)
            }else{
                liveData.value = AppState.Error(IllegalStateException("Ошибка"))
            }
        }

        //https://material.io/components/bottom-navigation/android#theming-a-bottom-navigation-bar
        override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable) {
            liveData.value = AppState.Error(IllegalStateException("onFailure"))
        }

    }

}