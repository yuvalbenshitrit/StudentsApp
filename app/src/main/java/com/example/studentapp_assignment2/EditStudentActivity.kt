package com.example.studentapp_assignment2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentapp_assignment2.R
import com.example.studentapp_assignment2.StudentsRecyclerViewActivity
import com.example.studentapp_assignment2.model.Model


class EditStudentActivity : AppCompatActivity() {
    lateinit var old_id: String
    lateinit var nameEditText: EditText
    lateinit var idEditText: EditText
    lateinit var phoneEditText: EditText
    lateinit var addressEditText: EditText
    lateinit var CheckBox: CheckBox
    lateinit var saveButton: Button
    lateinit var cancelButton: Button
    lateinit var deleteButton: Button
    lateinit var showMessageTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUi()
        insertStudentData()
        setupSaveListener()
        setupCancelListener()
        setupDeleteListener()
        Log.d("IntentCheck", "Activity: EditStudentActivity, Intent: ${intent.extras}")

    }
    private fun setUi() {
        nameEditText = findViewById(R.id.edit_student_activity_name_edit_text)
        idEditText = findViewById(R.id.edit_student_activity_id_edit_text)
        phoneEditText = findViewById(R.id.edit_student_activity_phone_edit_text)
        addressEditText = findViewById(R.id.edit_student_activity_address_edit_text)
        CheckBox = findViewById(R.id.edit_student_activity_check_box)
        saveButton = findViewById(R.id.edit_student_activity_save_button)
        deleteButton = findViewById(R.id.edit_student_activity_delete_button)
        cancelButton = findViewById(R.id.edit_student_activity_cancel_button)
        showMessageTextView = findViewById(R.id.edit_student_activity_show_message_text_view)
    }
    private fun insertStudentData() {
        nameEditText.setText(intent.getStringExtra("student name"))
        this.old_id = intent.getStringExtra("student id").toString()
        idEditText.setText(old_id)
        phoneEditText.setText(intent.getStringExtra("student phone"))
        addressEditText.setText(intent.getStringExtra("student address"))
        CheckBox.isChecked = intent.getBooleanExtra("student isChecked", false)
    }
    private fun setupSaveListener() {
        saveButton.setOnClickListener {
            // Check if name and id are not empty
            // If they are empty, show a message in the showMessageTextView
            // If they are not empty, save the student and show a message in the showMessageTextView
            val new_name = nameEditText.text.toString()
            val new_id = idEditText.text.toString()
            val new_phone = phoneEditText.text.toString()
            val new_address = addressEditText.text.toString()
            val new_isChecked = CheckBox.isChecked
            if (new_name.isEmpty() || new_id.isEmpty() || new_phone.isEmpty() || new_address.isEmpty()) {
                showMessageTextView.text = "Please fill in all fields"
            } else {
                Model.shared.updateStudent(old_id,new_id,new_name,new_phone,new_address,new_isChecked)
                val intent = Intent(this, StudentsRecyclerViewActivity::class.java)
                startActivity(intent)
            }
        }
    }
    private fun setupCancelListener() {
        cancelButton.setOnClickListener {
            // Finish the activity
            val intent = Intent(this, StudentsRecyclerViewActivity::class.java)
            startActivity(intent)

        }
    }

    private fun setupDeleteListener() {
        deleteButton.setOnClickListener {
            // Delete the student and finish the activity
            Model.shared.deleteStudent(old_id)
            val intent = Intent(this, StudentsRecyclerViewActivity::class.java)
            startActivity(intent)
        }
    }
}