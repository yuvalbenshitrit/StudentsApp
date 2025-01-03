package com.example.studentapp_assignment2.adapter

import android.content.Intent
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp_assignment2.R
import com.example.studentapp_assignment2.StudentDetailsActivity
import com.example.studentapp_assignment2.model.Student


class StudentsViewHolder (val itemView: View): RecyclerView.ViewHolder(itemView) {
    // Row properties
    private val nameTextView: TextView = itemView.findViewById(R.id.student_row_name_text_view)
    private val idTextView: TextView = itemView.findViewById(R.id.student_row_id_text_view)
    private val studentCheckBox: CheckBox = itemView.findViewById(R.id.student_row_check_box)

    private var student: Student? = null

    init {
        studentCheckBox.setOnClickListener {
            student?.isChecked = studentCheckBox.isChecked
        }
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, StudentDetailsActivity::class.java)
            intent.apply {
                putExtra("student name", student?.name)
                putExtra("student id", student?.id)
                putExtra("student phone", student?.phone)
                putExtra("student address", student?.address)
                putExtra("student isChecked", student?.isChecked)
            }
            itemView.context.startActivity(intent)
        }
    }

    fun bind(student: Student?) {
        this.student = student
        nameTextView.text = student?.name
        idTextView.text = student?.id
        studentCheckBox.isChecked = student?.isChecked ?: false

    }
}