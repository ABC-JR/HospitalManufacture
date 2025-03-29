package com.example.hospitalinfrastructuremanagement.viewmodels.departmentchiefviewmodel

import com.example.hospitalinfrastructuremanagement.viewmodels.StaffCategory
import com.example.hospitalinfrastructuremanagement.viewmodels.WardType_

interface DepartmentChiefEvent {
    data class SetStaffSalary(val salary: Double): DepartmentChiefEvent
    data class SetStaffHiringDate(val hiringDate: String): DepartmentChiefEvent
    data class SetStaffDepartmentID(val departmentID: Int): DepartmentChiefEvent
    data class SetStaffFirstName(val firstName: String): DepartmentChiefEvent
    data class SetStaffLastName(val lastName: String): DepartmentChiefEvent
    data class SetStaffID(val staffID: Int): DepartmentChiefEvent

    object IsAddingCabinet: DepartmentChiefEvent
    data class SetCabinetNumber(val roomNumber: Int): DepartmentChiefEvent
    data class SetCabinetStaffID(val staffID: Int): DepartmentChiefEvent
    object SaveCabinet: DepartmentChiefEvent
    object IsAddingWard: DepartmentChiefEvent
    data class SetWardNumber(val roomNumber: Int): DepartmentChiefEvent
    data class WardDoctorID(val staffID: Int): DepartmentChiefEvent
    data class WardType(val wardType: WardType_): DepartmentChiefEvent
    object SaveWard: DepartmentChiefEvent

    object IsAddingStaff: DepartmentChiefEvent
    data class SetAddingStaffSalary(val salary: Double): DepartmentChiefEvent
    data class SetAddingStaffHiringDate(val hiringDate: String): DepartmentChiefEvent
    data class SetAddingStaffDepartmentID(val departmentID: Int): DepartmentChiefEvent
    data class SetAddingStaffFirstName(val firstName: String): DepartmentChiefEvent
    data class SetAddingStaffLastName(val lastName: String): DepartmentChiefEvent
    data class SetAddingStaffID(val staffID: Int): DepartmentChiefEvent
    data class SetAddingStaffCategory(val staffCategory: StaffCategory): DepartmentChiefEvent
    data class SetAddingNurseManagerDoctorID(val managerDoctorID: Int): DepartmentChiefEvent
    data class SetAddingNurseWardNumber(val wardNumber: Int): DepartmentChiefEvent
    data class SetAddingDoctorExperience(val doctorExperience: Double): DepartmentChiefEvent
}