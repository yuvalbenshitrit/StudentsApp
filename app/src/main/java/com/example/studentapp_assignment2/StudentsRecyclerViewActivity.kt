package com.example.studentapp_assignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp_assignment2.adapter.StudentsRecyclerAdapter
import com.example.studentapp_assignment2.model.Model


class StudentsRecyclerViewActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var addStudentButton:Button
    lateinit var studentAdapter: StudentsRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_recycler_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUi()
        setupRecyclerView()
        setupAddStudentListener()
    }
    private fun setUi() {
        recyclerView = findViewById(R.id.studentrecycler_list)
        addStudentButton = findViewById(R.id.add_student_Button)
    }
    override fun onResume() {
        super.onResume()
        studentAdapter.notifyDataSetChanged()
    }
    private fun setupRecyclerView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentsRecyclerAdapter(Model.shared.students)
        recyclerView.adapter = studentAdapter
    }
    private fun setupAddStudentListener() {
        addStudentButton.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
    }


}