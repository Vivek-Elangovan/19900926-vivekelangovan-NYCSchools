package com.android.nycschools.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Schools(
    @SerializedName("dbn") var dbn: String? = null,
    @SerializedName("school_name") var schoolName: String? = null,
    @SerializedName("num_of_sat_test_takers") var satTestTakers: String? = null,
    @SerializedName("sat_critical_reading_avg_score") var readingSat: String? = null,
    @SerializedName("sat_math_avg_score") var mathSat: String? = null,
    @SerializedName("sat_writing_avg_score") var writingSat: String? = null
) : Serializable
