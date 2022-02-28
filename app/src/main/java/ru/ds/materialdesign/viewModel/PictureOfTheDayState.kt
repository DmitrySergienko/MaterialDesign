package ru.ds.materialdesign.viewModel

import ru.ds.materialdesign.repository.PictureOfTheDayResponseData

//состояние нашего приложения
sealed class PictureOfTheDayState {
    data class Success(val serverResponseData: PictureOfTheDayResponseData): PictureOfTheDayState()
    data class Error(val error: Throwable): PictureOfTheDayState()
    data class Loading(val progress: Int?): PictureOfTheDayState()
}