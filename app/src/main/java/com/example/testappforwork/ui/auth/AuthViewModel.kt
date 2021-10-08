package com.example.testappforwork.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testappforwork.models.*
import com.example.testappforwork.repository.Repository
import com.example.testappforwork.utilities.ResultLiveData
import com.example.testappforwork.utilities.ResultMutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _weatherLiveData: ResultMutableLiveData<WeatherResponse> = MutableLiveData()
    val weatherLiveData: ResultLiveData<WeatherResponse> = _weatherLiveData

    fun auth(cityId: Int) {
        _weatherLiveData.value = PendingResult()
        getWeather(cityId)
    }

    private fun getWeather(cityId: Int) {
        repository.getWeather(cityId) { result ->
            when (result) {
                is SuccessResult -> {
                    result.takeSuccess()?.let {
                        _weatherLiveData.value = SuccessResult(it)
                        _weatherLiveData.value = null
                    }
                }
                is ErrorResult -> {
                    _weatherLiveData.value = ErrorResult()
                }
                is PendingResult -> {
                    _weatherLiveData.value = PendingResult()
                }
            }
        }
    }

    override fun onCleared() {
        repository.clear()
        super.onCleared()
    }
}