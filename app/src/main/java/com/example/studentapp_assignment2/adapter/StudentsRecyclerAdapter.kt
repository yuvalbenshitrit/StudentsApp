package com.example.studentapp_assignment2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp_assignment2.R
import com.example.studentapp_assignment2.model.Student


class StudentsRecyclerAdapter(private val students: MutableList<Student>): RecyclerView.Adapter<StudentsViewHolder>(){
    // Called when RecyclerView needs a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.student_list_row, parent, false)
        return StudentsViewHolder(view)
    }
    // Called to display the data at the specified position
    override fun onBindViewHolder(holder: StudentsViewHolder, position: Int) {
        val student = students.get(position)
        holder.bind(student)

    }

    override fun getItemCount(): Int {
        return students.size
    }

}