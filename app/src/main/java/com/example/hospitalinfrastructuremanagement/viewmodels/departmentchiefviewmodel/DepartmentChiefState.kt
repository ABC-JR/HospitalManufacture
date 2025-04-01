package com.example.hospitalinfrastructuremanagement.viewmodels.departmentchiefviewmodel

import com.example.hospitalinfrastructuremanagement.room.entities.Cabinet
import com.example.hospitalinfrastructuremanagement.room.entities.DepartmentChief
import com.example.hospitalinfrastructuremanagement.room.entities.Doctor
import com.example.hospitalinfrastructuremanagement.room.entities.Nurse
import com.example.hospitalinfrastructuremanagement.room.entities.Room
import com.example.hospitalinfrastructuremanagement.room.entities.Staff
import com.example.hospitalinfrastructuremanagement.room.entities.Ward
import com.example.hospitalinfrastructuremanagement.viewmodels.WardType_

data class DepartmentChiefState(
    val staffSalary: Double = 0.0,
    val staffHiringDate: String = "",
    val staffDepartmentID: Int = 0,
    val staffFirstName: String = "",
    val staffLastName: String = "",
    val staffID: Int = 0,
    // Profile Info
    val rooms: List<Room> = emptyList(),

    val isAddingCabinet: Boolean = false,
    val cabinetNumber: Int = 0,
    val cabinetStaffID: Int = 0,
    val cabinets: List<Cabinet> = emptyList(),

    val isAddingWard: Boolean = false,
    val wardNumber: Int = 0,
    val wardDoctorID: Int = 0,
    val wardType: WardType_ = WardType_.ACCIDENT_EMERGENCY,
    val wards: List<Ward> = emptyList(),

    // Adding Room/Cabinet/Ward
    val isAddingNurse: Boolean = false,
    val isAddingDoctor: Boolean = false,
    val isAddingDepartmentChief: Boolean = false,
    val addingStaffSalary: Double = 0.0,
    val addingStaffHiringDate: String = "",
    val addingStaffDepartmentID: Int = 0,
    val addingStaffFirstName: String = "",
    val addingStaffLastName: String = "",
    val addingStaffID: Int = 0,
    val staff: List<Staff> = emptyList(),

    val addingNurseManagerDoctorID: Int = 0,
    val addingNurseWardNumber: Int = 0,
    val addingDoctorExperience: Double = 0.0,
    val nurses: List<Nurse> = emptyList(),
    val doctors: List<Doctor> = emptyList(),
    val departmentChiefs: List<DepartmentChief> = emptyList()

    // Adding Nurse/Doctor
)
