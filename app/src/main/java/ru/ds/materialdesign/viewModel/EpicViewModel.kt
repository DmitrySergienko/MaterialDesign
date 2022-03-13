package ru.ds.materialdesign.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.ds.materialdesign.BuildConfig
import ru.ds.materialdesign.repository.RetrofitImpl
import ru.ds.materialdesign.repository.epic.EarthEpicServerResponseData
import ru.ds.materialdesign.viewModel.MarsViewModel.Companion.UNKNOWN_ERROR


private const val API_ERROR = "You need API Key"

class EpicViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val retrofitImp: RetrofitImpl = RetrofitImpl(),
): ViewModel() {

    fun getLiveData(): LiveData<AppState> {
        return liveDataToObserve
    }
    companion object {
        private const val UNKNOWN_ERROR_EPIC = "Unidentified error"
    }

    fun sendServerRequest(){
        liveDataToObserve.postValue(AppState.Loading)
        val apiKey = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            AppState.Error(Throwable(API_ERROR))
        } else {
            retrofitImp.getEPIC(apiKey, epicCallback)
        }
    }

      private val epicCallback = object : Callback<List<EarthEpicServerResponseData>> {

        override fun onResponse(
                call: Call<List<EarthEpicServerResponseData>>,
                response: Response<List<EarthEpicServerResponseData>>,
        ) {
            if (response.isSuccessful && response.body() != null) {
                liveDataToObserve.postValue(AppState.SuccessEpic(response.body()!!))
            } else {
                val message = response.message()
                if (message.isNullOrEmpty()) {
                    liveDataToObserve.postValue(AppState.Error(Throwable(UNKNOWN_ERROR)))
                } else {
                    liveDataToObserve.postValue(AppState.Error(Throwable(message)))
                }
            }
        }

        override fun onFailure(call: Call<List<EarthEpicServerResponseData>>, t: Throwable) {
            liveDataToObserve.postValue(AppState.Error(t))
        }
    }


}