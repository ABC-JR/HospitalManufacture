package com.example.hospitalinfrastructuremanagement.viewmodels.departmentchiefviewmodel

import com.example.hospitalinfrastructuremanagement.room.entities.Cabinet
import com.example.hospitalinfrastructuremanagement.room.entities.DepartmentChief
import com.example.hospitalinfrastructuremanagement.room.entities.Doctor
import com.example.hospitalinfrastructuremanagement.room.entities.Nurse
import com.example.hospitalinfrastructuremanagement.room.entities.Room
import com.example.hospitalinfrastructuremanagement.room.entities.Staff
import com.example.hospitalinfrastructuremanagement.room.entities.Ward
import com.example.hospitalinfrastructuremanagement.viewmodels.StaffCategory
import com.example.hospitalinfrastructuremanagement.viewmodels.WardType_

data class DepartmentChiefState(
    var staffSalary: Double = 0.0,
    var staffHiringDate: String = "",
    var staffDepartmentID: Int = 0,
    var staffFirstName: String = "",
    var staffLastName: String = "",
    var staffID: Int = 0,
    // Profile Info
    var rooms: List<Room> = emptyList(),

    var isAddingCabinet: Boolean = false,
    var cabinetNumber: Int = 0,
    var cabinetStaffID: Int = 0,
    var cabinets: List<Cabinet> = emptyList(),

    var isAddingWard: Boolean = false,
    var wardNumber: Int = 0,
    var wardDoctorID: Int = 0,
    var wardType: WardType_ = WardType_.ACCIDENT_EMERGENCY,
    var wards: List<Ward> = emptyList(),

    // Adding Room/Cabinet/Ward
    var isAddingStaff: Boolean = false,
    var addingStaffSalary: Double = 0.0,
    var addingStaffHiringDate: String = "",
    var addingStaffDepartmentID: Int = 0,
    var addingStaffFirstName: String = "",
    var addingStaffLastName: String = "",
    var addingStaffID: Int = 0,
    var staff: List<Staff> = emptyList(),

    var addingStaffCategory: StaffCategory = StaffCategory.NURSE,
    var addingNurseManagerDoctorID: Int = 0,
    var addingNurseWardNumber: Int = 0,
    var addingDoctorExperience: Double = 0.0,
    var nurses: List<Nurse> = emptyList(),
    var doctors: List<Doctor> = emptyList(),
    var departmentChiefs: List<DepartmentChief> = emptyList()

    // Adding Nurse/Doctor
)
