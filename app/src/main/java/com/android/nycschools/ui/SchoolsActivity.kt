package com.android.nycschools.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.nycschools.ClickListener
import com.android.nycschools.Utils
import com.android.nycschools.api.RetrofitService
import com.android.nycschools.api.SchoolRepository
import com.android.nycschools.api.SchoolViewModelFactory
import com.android.nycschools.api.Schools
import com.android.nycschools.databinding.ActivitySchoolsBinding

class SchoolsActivity : AppCompatActivity() {

    private lateinit var viewModel: SchoolsViewModel
    private val adapter = SchoolsAdapter()
    lateinit var binding: ActivitySchoolsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySchoolsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = RetrofitService.getInstance()
        val schoolRepository = SchoolRepository(retrofitService)
        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            SchoolViewModelFactory(schoolRepository)
        )[SchoolsViewModel::class.java]
        viewModel.schoolList.observe(this) { result ->
            result?.let { adapter.setSchools(it) }
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })
        adapter.setSchoolClickListener(object : ClickListener<Schools> {
            override fun onCLick(data: Schools) {
                val intent = Intent(this@SchoolsActivity, SchoolDetailActivity::class.java)
                intent.putExtra("SCHOOL_DETAIL_EXTRA", data)
                startActivity(intent)
            }

        })
        if (Utils.isNetworkAvailable(this))
            viewModel.getSchools()
        else Toast.makeText(this, "No Internet. Please try again later.", Toast.LENGTH_SHORT).show()
    }
}