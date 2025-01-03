package com.example.studentapp_assignment2.model


class Model private constructor() // Primary constructor is private
{
    val students: MutableList<Student> = ArrayList() // Setting Property for students
    companion object {
        // When writing Model.shared for the first time, the primary Model() constructor is called
        // which takes no arguments. then, init block is called which
        // initializes the students list with 20 students.
        // when the Model.shared is called for the second time, the same instance is returned (no constructor is called)
        var shared = Model()


    }
    fun addStudent(student: Student) {
        students.add(student)
    }
    fun updateStudent(id: String,new_id: String, name: String, phone: String, address: String, isChecked: Boolean) {
        val student = students.find { it.id == id }
        student?.let {
            it.id = new_id
            it.name = name
            it.phone = phone
            it.address = address
            it.isChecked = isChecked
        }
    }
    fun deleteStudent(id: String) {
        students.removeAll { it.id == id }
    }


}


