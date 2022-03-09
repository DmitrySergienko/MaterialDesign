package ru.ds.materialdesign.viewModel

import ru.ds.materialdesign.repository.PictureOfTheDayResponseData
import ru.ds.materialdesign.repository.dto.mars.MarsPhotosServerResponseData

//состояние нашего приложения
sealed class AppState {
    data class Success(val serverResponseData: PictureOfTheDayResponseData): AppState()
    data class SuccessMars(val serverResponseData: MarsPhotosServerResponseData):AppState()

    data class Error(val error: Throwable): AppState()
    data class Loading(val progress: Int?): AppState()
}