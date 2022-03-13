package ru.ds.materialdesign.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.ds.materialdesign.BuildConfig
import ru.ds.materialdesign.repository.RetrofitImpl
import ru.ds.materialdesign.repository.epic.EpicDTO

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

        liveDataToObserve.value = AppState.Loading(0) // отправляем стстояние загрузки
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            liveDataToObserve.value = AppState.Error(Throwable("wrong key"))
        } else {
            retrofitImp.getEPIC(BuildConfig.NASA_API_KEY,epicCallback)
        }
    }
    val epicCallback = object : Callback<List<EpicDTO>> {

        override fun onFailure(call: Call<List<EpicDTO>>, t: Throwable) {
            liveDataToObserve.postValue(AppState.Error(t))
        }

        override fun onResponse(
            call: Call<List<EpicDTO>>,
            response: Response<List<EpicDTO>>
        ) {
            if (response.isSuccessful && response.body() != null) {
                liveDataToObserve.postValue(AppState.SuccessEpic(response.body()!!))
            } else {
                val message = response.message()
                if (message.isNullOrEmpty()) {
                    liveDataToObserve.postValue(AppState.Error(Throwable(EpicViewModel.UNKNOWN_ERROR_EPIC)))
                } else {
                    liveDataToObserve.postValue(AppState.Error(Throwable(message)))
                }
            }
        }
    }

}