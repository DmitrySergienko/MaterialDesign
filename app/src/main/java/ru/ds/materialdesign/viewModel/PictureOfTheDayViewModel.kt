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
    private val pictureOfTheDayRemoteImp: PictureOfTheDayRemoteImp = PictureOfTheDayRemoteImp()
): ViewModel() {

    fun getLiveData():LiveData<PictureOfTheDayState>{
        return liveData
    }
    fun sendServerRequest(){
        liveData.postValue(PictureOfTheDayState.Loading(null))
        pictureOfTheDayRemoteImp.getRetrofitImp().getPictureOfTheDay(BuildConfig.NASA_API_KEY).enqueue(
            object : Callback<PictureOfTheDayResponseData>{
                override fun onResponse(
                    call: Call<PictureOfTheDayResponseData>,
                    response: Response<PictureOfTheDayResponseData>
                ) {
                    if (response.isSuccessful&&response.body()!=null){
                        response.body()?.let {
                            liveData.postValue(PictureOfTheDayState.Success(it))
                        }
                    }else{
                        //TODO HW
                        //если response body is empty (no picture today)
                    }
                }

                override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable) {
                    //TODO HW
                    //Toast.makeText(,"No response from the URL server", Toast.LENGTH_SHORT).show()
                }

            }
        )

    }
}