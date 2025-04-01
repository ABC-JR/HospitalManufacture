package com.example.hospitalinfrastructuremanagement.viewmodels.nurseviewmodel

import com.example.hospitalinfrastructuremanagement.room.entities.Appointment

data class NurseState(
    val staffSalary: Double = 0.0,
    val staffHiringDate: String = "",
    val staffDepartmentID: Int = 0,
    val staffFirstName: String = "",
    val staffLastName: String = "",
    val staffID: Int = 0,
    val managerDoctorID: Int = 0,
    val wardNumber: Int = 0,

    // Profile

    val isAddingAppointment: Boolean = false,
    val appointmentPatientID: Int = 0,
    val appointmentStaffID: Int = 0,
    val cabinetNumber: Int = 0,
    val appointmentDateTime: String = "",
    val appointments: List<Appointment> = emptyList(),

    // Appointment
)
