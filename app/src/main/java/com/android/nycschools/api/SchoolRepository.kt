package com.android.nycschools.api

class SchoolRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getSchools(): NetworkState<List<Schools>> {
        val response = retrofitService.getSchools()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }
}