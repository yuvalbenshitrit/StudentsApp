package com.example.studentapp_assignment2


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StudentDetailsActivity : AppCompatActivity() {
    lateinit var id_textview: TextView
    lateinit var name_textview: TextView
    lateinit var phone_textview: TextView
    lateinit var address_textview: TextView
    lateinit var ischecked_checkbox: CheckBox
    lateinit var Back_Button: Button
    lateinit var Edit_Button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupUi()
        insertStudentData()
        setupBackButtonListener()
        setupEditButtonListener()
        Log.d("IntentCheck", "Activity: StudentsRecyclerViewActivity, Intent: ${intent.extras}")

    }
    private fun setupUi() {
        id_textview = findViewById(R.id.student_details_activity_id_textview)
        name_textview = findViewById(R.id.student_details_activity_name_textview)
        phone_textview = findViewById(R.id.student_details_activity_phone_textview)
        address_textview = findViewById(R.id.student_details_activity_address_textview)
        ischecked_checkbox = findViewById(R.id.student_details_activity_ischecked_checkbox)
        Back_Button = findViewById(R.id.student_details_activity_back_button)
        Edit_Button = findViewById(R.id.student_details_activity_edit_button)
    }
    private fun insertStudentData() {
        name_textview.setText(intent.getStringExtra("student name"))
        id_textview.setText(intent.getStringExtra("student id"))
        phone_textview.setText(intent.getStringExtra("student phone"))
        address_textview.setText(intent.getStringExtra("student address"))
        ischecked_checkbox.isChecked = intent.getBooleanExtra("student isChecked", false)
        ischecked_checkbox.isClickable = false
    }
    private fun setupBackButtonListener() {
        Back_Button.setOnClickListener {
            finish()
        }
    }
    private fun setupEditButtonListener() {
        Edit_Button.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("student name", name_textview.text.toString())
            intent.putExtra("student id", id_textview.text.toString())
            intent.putExtra("student phone", phone_textview.text.toString())
            intent.putExtra("student address", address_textview.text.toString())
            intent.putExtra("student isChecked", ischecked_checkbox.isChecked)
            startActivity(intent)
        }
    }
}