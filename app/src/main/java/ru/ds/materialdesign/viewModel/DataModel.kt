package ru.ds.materialdesign.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel : ViewModel() {

    val textDescriptionFromNASA: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}