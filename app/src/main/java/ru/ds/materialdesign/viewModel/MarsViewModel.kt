package ru.ds.materialdesign.viewModel

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.ds.materialdesign.BuildConfig
import ru.ds.materialdesign.repository.RetrofitImpl
import ru.ds.materialdesign.repository.dto.mars.MarsPhotosServerResponseData
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MarsViewModel(
        private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
        private val retrofitImp: RetrofitImpl = RetrofitImpl(),

):ViewModel() {
        fun getLiveData():LiveData<AppState>{
            return liveDataToObserve
        }
    companion object {
        const val UNKNOWN_ERROR = "Unidentified error"
    }

    fun sendServerRequest(){
        val earthDate = getDayBeforeYesterday()
        liveDataToObserve.postValue(AppState.Loading) // отправляем стстояние загрузки
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            liveDataToObserve.value = AppState.Error(Throwable("wrong key"))
        } else {
            retrofitImp.getMarsPictureByDate(earthDate,BuildConfig.NASA_API_KEY,marsCallback)
        }
    }

    val marsCallback = object : Callback<MarsPhotosServerResponseData> {

        override fun onResponse(
                call: Call<MarsPhotosServerResponseData>,
                response: Response<MarsPhotosServerResponseData>,
        ) {
            if (response.isSuccessful && response.body() != null) {
                liveDataToObserve.postValue(AppState.SuccessMars(response.body()!!))
            } else {
                val message = response.message()
                if (message.isNullOrEmpty()) {
                    liveDataToObserve.postValue(AppState.Error(Throwable(UNKNOWN_ERROR)))
                } else {
                    liveDataToObserve.postValue(AppState.Error(Throwable(message)))
                }
            }
        }

        override fun onFailure(call: Call<MarsPhotosServerResponseData>, t: Throwable) {
            liveDataToObserve.postValue(AppState.Error(t))
        }
    }


    private fun getDayBeforeYesterday(): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val yesterday = LocalDateTime.now().minusDays(2)
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            return yesterday.format(formatter)
        } else {
            val cal: Calendar = Calendar.getInstance()
            val s = SimpleDateFormat("yyyy-MM-dd")
            cal.add(Calendar.DAY_OF_YEAR, -2)
            return s.format(cal.time)
        }
    }
}