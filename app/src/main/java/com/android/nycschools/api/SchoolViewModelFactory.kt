package com.android.nycschools.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.nycschools.ui.SchoolsViewModel

@Suppress("UNCHECKED_CAST")
class SchoolViewModelFactory constructor(private val repository: SchoolRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SchoolsViewModel::class.java)) {
            SchoolsViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}