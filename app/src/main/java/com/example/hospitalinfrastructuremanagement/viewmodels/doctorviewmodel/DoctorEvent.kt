package com.example.hospitalinfrastructuremanagement.viewmodels.doctorviewmodel


interface DoctorEvent {
    data class SetStaffSalary(val salary: Double): DoctorEvent
    data class SetStaffHiringDate(val hiringDate: String): DoctorEvent
    data class SetStaffDepartmentID(val departmentID: Int): DoctorEvent
    data class SetStaffFirstName(val firstName: String): DoctorEvent
    data class SetStaffLastName(val lastName: String): DoctorEvent
    data class SetStaffID(val staffID: Int): DoctorEvent
    data class SetDoctorExperience(val experience: Double): DoctorEvent

    // Profile

    object IsAddingAppointment: DoctorEvent
    data class SetAppointmentPatientID(val patientID: Int): DoctorEvent
    data class SetAppointmentStaffID(val staffID: Int): DoctorEvent
    data class SetAppointmentCabinetNumber(val cabinetNumber: Int): DoctorEvent
    data class SetAppointmentDateTime(val dateTime: String): DoctorEvent
    object SaveAppointment: DoctorEvent

    // Appointment

    object IsAddingAccidentEmergency: DoctorEvent
    object IsAddingSurgery: DoctorEvent
    object IsAddingPaediatrics: DoctorEvent
    data class SetProcedurePatientID(val patientID: Int): DoctorEvent
    data class SetProcedureStaffID(val staffID: Int): DoctorEvent
    data class SetProcedureWardNumber(val wardNumber: Int): DoctorEvent
    data class SetProcedureDateTime(val dateTime: String): DoctorEvent

    data class SetEmergencyLevel(val emergencyLevel: Byte): DoctorEvent
    object SaveAccidentEmergency: DoctorEvent
    data class SetSurgeryType(val type: String): DoctorEvent
    object SaveSurgery: DoctorEvent
    data class SetAgeCategory(val ageCategory: String): DoctorEvent
    object SavePaediatrics: DoctorEvent
}