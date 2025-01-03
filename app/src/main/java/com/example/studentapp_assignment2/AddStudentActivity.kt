package com.example.studentapp_assignment2

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentapp_assignment2.model.Model
import com.example.studentapp_assignment2.model.Student

class AddStudentActivity () : AppCompatActivity() {
    lateinit var nameEditText: EditText
    lateinit var idEditText: EditText
    lateinit var phoneEditText: EditText
    lateinit var addressEditText: EditText
    lateinit var CheckBox: CheckBox
    lateinit var saveButton: Button
    lateinit var cancelButton: Button
    lateinit var showMessageTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUi()
        setupSaveListener()
        setupCancelListener()
    }
    private fun setUi() {
        nameEditText = findViewById(R.id.add_student_activity_name_edit_text)
        idEditText = findViewById(R.id.add_student_activity_id_edit_text)
        phoneEditText = findViewById(R.id.add_student_activity_phone_edit_text)
        addressEditText = findViewById(R.id.add_student_activity_address_edit_text)
        CheckBox = findViewById(R.id.add_student_activity_check_box)
        saveButton = findViewById(R.id.add_student_activity_save_button)
        cancelButton = findViewById(R.id.add_student_activity_cancel_button)
        showMessageTextView = findViewById(R.id.add_student_activity_show_message_text_view)
    }
    private fun setupSaveListener() {
        saveButton.setOnClickListener {
            // Check if name and id are not empty
            // If they are empty, show a message in the showMessageTextView
            // If they are not empty, save the student and show a message in the showMessageTextView
            val name = nameEditText.text.toString()
            val id = idEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val address = addressEditText.text.toString()
            val isChecked = CheckBox.isChecked
            if (name.isEmpty() || id.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                showMessageTextView.setText("Please fill in all fields")
            } else {
                if (Model.shared.students.any { it.id == id }) {
                    showMessageTextView.setText("Student with this ID already exists")
                }
                else {
                    Model.shared.addStudent(Student(name, id, phone, address, isChecked))
                    finish()
                }
            }
        }
    }
    private fun setupCancelListener() {
        cancelButton.setOnClickListener {
            finish()
        }
    }
}