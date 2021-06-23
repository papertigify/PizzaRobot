package com.example.robotdeliveryman.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.robotdeliveryman.repository.Repository
import com.example.robotdeliveryman.utils.Resource
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    val route: MutableLiveData<Resource<Int>> = MutableLiveData()

    fun getRoute(inputString: String){
        route.postValue(Resource.loading(null))
        // call to the repository
        // handle the response
        route.postValue(Resource.success(0))
    }
}