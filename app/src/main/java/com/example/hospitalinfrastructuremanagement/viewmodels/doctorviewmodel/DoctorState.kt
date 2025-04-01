package com.example.hospitalinfrastructuremanagement.viewmodels.doctorviewmodel

import com.example.hospitalinfrastructuremanagement.room.entities.AccidentEmergency
import com.example.hospitalinfrastructuremanagement.room.entities.Appointment
import com.example.hospitalinfrastructuremanagement.room.entities.Paediatrics
import com.example.hospitalinfrastructuremanagement.room.entities.Surgery

data class DoctorState(
    val staffSalary: Double = 0.0,
    val staffHiringDate: String = "",
    val staffDepartmentID: Int = 0,
    val staffFirstName: String = "",
    val staffLastName: String = "",
    val staffID: Int = 0,
    val doctorExperience: Double = 0.0,

    // Profile

    val isAddingAppointment: Boolean = false,
    val appointmentPatientID: Int = 0,
    val appointmentStaffID: Int = 0,
    val cabinetNumber: Int = 0,
    val appointmentDateTime: String = "",
    val appointments: List<Appointment> = emptyList(),

    // Appointment

    val isAddingAccidentEmergency: Boolean = false,
    val isAddingSurgery: Boolean = false,
    val isAddingPaediatrics: Boolean = false,
    val procedurePatientID: Int = 0,
    val procedureStaffID: Int = 0,
    val wardNumber: Int = 0,
    val procedureDateTime: String = "",

    val emergencyLevel: Byte = 0,
    val surgeryType: String = "",
    val ageCategory: String = "",

    val accidentEmergencies: List<AccidentEmergency> = emptyList(),
    val surgeries: List<Surgery> = emptyList(),
    val allPaediatrics: List<Paediatrics> = emptyList()

    // Medical procedure
)
