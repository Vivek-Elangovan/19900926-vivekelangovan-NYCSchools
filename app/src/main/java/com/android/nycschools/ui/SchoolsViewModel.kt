package com.android.nycschools.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.nycschools.api.NetworkState
import com.android.nycschools.api.SchoolRepository
import com.android.nycschools.api.Schools
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SchoolsViewModel(private val schoolRepository: SchoolRepository) : ViewModel() {
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage
    val schoolList = MutableLiveData<List<Schools>?>()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()

    /**
     * This function request the list of NYC schools
     */
    fun getSchools() {
        viewModelScope.launch {
            when (val response = schoolRepository.getSchools()) {
                is NetworkState.Success -> {
                    schoolList.postValue(response.data)
                    loading.value = false
                }
                is NetworkState.Error -> {
                    onError()
                }
            }
        }
    }

    private fun onError() {
        _errorMessage.value = "Oops! server not responding."
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}