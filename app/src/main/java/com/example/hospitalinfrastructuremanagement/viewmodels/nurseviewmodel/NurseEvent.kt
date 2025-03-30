package com.example.hospitalinfrastructuremanagement.viewmodels.nurseviewmodel



interface NurseEvent {
    data class SetStaffSalary(val salary: Double): NurseEvent
    data class SetStaffHiringDate(val hiringDate: String): NurseEvent
    data class SetStaffDepartmentID(val departmentID: Int): NurseEvent
    data class SetStaffFirstName(val firstName: String): NurseEvent
    data class SetStaffLastName(val lastName: String): NurseEvent
    data class SetStaffID(val staffID: Int): NurseEvent
    data class SetManagerDoctorID(val doctorID: Int): NurseEvent
    data class SetWardNumber(val wardNumber: Int): NurseEvent

    // Profile
    
    object IsAddingAppointment: NurseEvent
    data class SetAppointmentPatientID(val patientID: Int): NurseEvent
    data class SetAppointmentStaffID(val staffID: Int): NurseEvent
    data class SetAppointmentCabinetNumber(val cabinetNumber: Int): NurseEvent
    data class SetAppointmentDateTime(val dateTime: String): NurseEvent
    object SaveAppointment: NurseEvent
}