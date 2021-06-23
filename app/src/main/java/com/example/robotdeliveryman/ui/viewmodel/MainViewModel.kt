package com.example.robotdeliveryman.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.robotdeliveryman.repository.Repository
import com.example.robotdeliveryman.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _route: MutableLiveData<Resource<String>> = MutableLiveData()
    val route: LiveData<Resource<String>> = _route

    fun getRoute(inputString: String){
        viewModelScope.launch {
            _route.postValue(Resource.loading(null))
            val result = repository.getRoute(inputString)
            _route.postValue(result)
        }
    }
}