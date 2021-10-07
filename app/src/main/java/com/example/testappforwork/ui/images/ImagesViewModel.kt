package com.example.testappforwork.ui.images

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testappforwork.models.*
import com.example.testappforwork.repository.Repository
import com.example.testappforwork.utilities.ResultLiveData
import com.example.testappforwork.utilities.ResultMutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val images = ArrayList<Image>()
    private val _imagesLiveData: ResultMutableLiveData<ArrayList<Image>> = MutableLiveData(PendingResult())
    val imagesLiveData: ResultLiveData<ArrayList<Image>> = _imagesLiveData

    private var page = 1

    init {
        getImages()
    }

    fun getImages() {
        repository.getImages(page, LIMIT) { result ->
            when (result) {
                is SuccessResult -> {
                    result.takeSuccess()?.let { images.addAll(it) }
                    _imagesLiveData.value = SuccessResult(images)
                    page += 1
                }
                is ErrorResult -> {
                    _imagesLiveData.value = ErrorResult()
                }
                is PendingResult -> {
                    _imagesLiveData.value = PendingResult()
                }
            }
        }
    }

    override fun onCleared() {
        repository.clear()
        super.onCleared()
    }

    companion object {
        private const val TAG = "ImagesViewModel"
        private const val LIMIT = 20

    }
}