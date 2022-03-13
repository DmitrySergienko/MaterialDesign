package ru.ds.materialdesign.viewModel

import ru.ds.materialdesign.repository.PictureOfTheDayResponseData
import ru.ds.materialdesign.repository.dto.mars.MarsPhotosServerResponseData
import ru.ds.materialdesign.repository.epic.EpicDTO

//состояние нашего приложения
sealed class AppState {
    data class Success(val serverResponseData: PictureOfTheDayResponseData): AppState()
    data class SuccessMars(val serverResponseData: MarsPhotosServerResponseData):AppState()
    data class SuccessEpic(val serverResponseData: List<EpicDTO>):AppState()

    data class Error(val error: Throwable): AppState()
    data class Loading(val progress: Int?): AppState()
}