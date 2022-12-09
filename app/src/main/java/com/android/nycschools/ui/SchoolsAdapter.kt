package com.android.nycschools.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.nycschools.ClickListener
import com.android.nycschools.api.Schools
import com.android.nycschools.databinding.ItemSchoolBinding

/**
 * This adapter holds the List of schools.
 */
class SchoolsAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var schoolList = mutableListOf<Schools>()
    var clickListener: ClickListener<Schools>? = null

    /**
     * This function will set the school list from the viewmodel
     */
    fun setSchools(schools: List<Schools>) {
        this.schoolList = schools.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSchoolBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val school = schoolList[position]
        holder.binding.schoolName.text = school.schoolName
        holder.binding.schoolName.setOnClickListener {
            clickListener?.onCLick(school)
        }
    }

    override fun getItemCount(): Int {
        return schoolList.size
    }

    /**
     * Click Listener for School
     */
    fun setSchoolClickListener(clickListener: ClickListener<Schools>) {
        this.clickListener = clickListener
    }
}

class MainViewHolder(val binding: ItemSchoolBinding) : RecyclerView.ViewHolder(binding.root)
