package com.android.nycschools.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("f9bf-2cp4.json")
    suspend fun getSchools(): Response<List<Schools>>

    companion object {
        var retrofitService: RetrofitService? = null

        /**
         * This instance build the retrofit with the base url
         * @return retrofitService RetrofitService
         */
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://data.cityofnewyork.us/resource/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}