package com.android.nycschools.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.nycschools.api.Schools
import com.android.nycschools.databinding.ActivitySchoolDetailBinding

class SchoolDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivitySchoolDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySchoolDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val schoolDetail = intent.getSerializableExtra("SCHOOL_DETAIL_EXTRA") as? Schools
        binding.schoolName.text = schoolDetail?.schoolName
        binding.mathSat.text = buildString {
            append("Math Sat : ")
            append(schoolDetail?.mathSat)
        }
        binding.readingSat.text = buildString {
            append("Reading Sat : ")
            append(schoolDetail?.readingSat)
        }
        binding.writingSat.text = buildString {
            append("Writing Sat : ")
            append(schoolDetail?.writingSat)
        }
    }
}