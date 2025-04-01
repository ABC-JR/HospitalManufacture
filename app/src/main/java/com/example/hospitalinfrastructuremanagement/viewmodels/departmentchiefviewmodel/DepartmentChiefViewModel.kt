package com.example.hospitalinfrastructuremanagement.viewmodels.departmentchiefviewmodel

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalinfrastructuremanagement.room.HospitalDao
import com.example.hospitalinfrastructuremanagement.room.entities.Cabinet
import com.example.hospitalinfrastructuremanagement.room.entities.DepartmentChief
import com.example.hospitalinfrastructuremanagement.room.entities.Doctor
import com.example.hospitalinfrastructuremanagement.room.entities.Nurse
import com.example.hospitalinfrastructuremanagement.room.entities.Room
import com.example.hospitalinfrastructuremanagement.room.entities.Staff
import com.example.hospitalinfrastructuremanagement.room.entities.Ward
import com.example.hospitalinfrastructuremanagement.viewmodels.WardType_
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DepartmentChiefViewModel(val dao: HospitalDao): ViewModel() {
    private val _rooms = dao.getRooms().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _wards = dao.getWards().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _cabinets = dao.getCabinets().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _staff = dao.getStaff().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _nurses = dao.getNurses().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _doctors = dao.getDoctors().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _departmentChiefs = dao.getDepartmentChiefs().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(DepartmentChiefState())

    private val _state1 = combine(_rooms, _wards, _cabinets, _staff, _state)
    { rooms, wards, cabinets, staff, state ->
        state.copy(
            rooms = rooms,
            wards = wards,
            cabinets = cabinets,
            staff = staff
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), DepartmentChiefState())

    val state = combine(_nurses, _doctors, _departmentChiefs, _state, _state1)
    { nurses, doctors, departmentChiefs, state, state1 ->
        state.copy(
            rooms = state1.rooms,
            wards = state1.wards,
            cabinets = state1.cabinets,
            staff = state1.staff,
            nurses = nurses,
            doctors = doctors,
            departmentChiefs = departmentChiefs
        )
    }

    fun onEvent(event: DepartmentChiefEvent) {
        when(event) {
            is DepartmentChiefEvent.SetStaffSalary -> {
                _state.update {
                    it.copy(
                        staffSalary = event.salary
                    )
                }
            }
            is DepartmentChiefEvent.SetStaffHiringDate -> {
                _state.update {
                    it.copy(
                        staffHiringDate = event.hiringDate
                    )
                }
            }
            is DepartmentChiefEvent.SetStaffDepartmentID -> {
                _state.update {
                    it.copy(
                        staffDepartmentID = event.departmentID
                    )
                }
            }
            is DepartmentChiefEvent.SetStaffFirstName -> {
                _state.update {
                    it.copy(
                        staffFirstName = event.firstName
                    )
                }
            }
            is DepartmentChiefEvent.SetStaffLastName -> {
                _state.update {
                    it.copy(
                        staffLastName = event.lastName
                    )
                }
            }
            is DepartmentChiefEvent.SetStaffID -> {
                _state.update {
                    it.copy(
                        staffID = event.staffID
                    )
                }
            }

            // Reusable

            DepartmentChiefEvent.IsAddingCabinet -> {
                _state.update {
                    it.copy(
                        isAddingCabinet = true
                    )
                }
            }
            is DepartmentChiefEvent.SetCabinetNumber -> {
                _state.update {
                    it.copy(
                        cabinetNumber = event.roomNumber
                    )
                }
            }
            is DepartmentChiefEvent.SetCabinetStaffID -> {
                _state.update {
                    it.copy(
                        cabinetStaffID = event.staffID
                    )
                }
            }
            DepartmentChiefEvent.SaveCabinet -> {
                if(_state.value.cabinetNumber <= 0) {
                    return;
                }
                try {
                    viewModelScope.launch {
                        dao.upsertRoom(Room(roomNumber = _state.value.cabinetNumber))
                        dao.upsertCabinet(Cabinet(roomNumber = _state.value.cabinetNumber, staffID = _state.value.cabinetStaffID))
                    }
                } catch (_: SQLiteConstraintException) {

                } finally {
                    _state.update {
                        it.copy(
                            isAddingCabinet = false,
                            cabinetNumber = 0,
                            cabinetStaffID = 0
                        )
                    }
                }
            }
            DepartmentChiefEvent.IsAddingWard -> {
                _state.update {
                    it.copy(
                        isAddingWard = true,
                        cabinetNumber = 0,
                        cabinetStaffID = 0
                    )
                }
            }
            is DepartmentChiefEvent.SetWardNumber -> {
                _state.update {
                    it.copy(
                        wardNumber = event.roomNumber
                    )
                }
            }
            is DepartmentChiefEvent.SetWardDoctorID -> {
                _state.update {
                    it.copy(
                        wardDoctorID = event.staffID
                    )
                }
            }
            is DepartmentChiefEvent.SetWardType -> {
                _state.update {
                    it.copy(
                        wardType = event.wardType
                    )
                }
            }
            DepartmentChiefEvent.SaveWard -> {
                if(_state.value.wardNumber <= 0) {
                    return
                }
                val wardType: String = when(_state.value.wardType) {
                    WardType_.ACCIDENT_EMERGENCY -> { "Accident&Emergency" }
                    WardType_.SURGERY -> { "Surgery" }
                    WardType_.PAEDIATRICS -> { "Paediatrics" }
                }
                try {
                    viewModelScope.launch {
                        dao.upsertRoom(Room(roomNumber = _state.value.wardNumber))
                        dao.upsertWard(Ward(
                            roomNumber = _state.value.wardNumber,
                            doctorID = _state.value.wardDoctorID,
                            type = wardType)
                        )
                    }
                } catch (_: SQLiteConstraintException) {

                } finally {
                    _state.update {
                        it.copy(
                            isAddingWard = false,
                            wardNumber = 0,
                            wardDoctorID = 0,
                            wardType = WardType_.ACCIDENT_EMERGENCY
                        )
                    }
                }
            }
            DepartmentChiefEvent.IsAddingNurse -> {
                _state.update {
                    it.copy(
                        isAddingNurse = true
                    )
                }
            }
            DepartmentChiefEvent.IsAddingDoctor -> {
                _state.update {
                    it.copy(
                        isAddingDoctor = true
                    )
                }
            }
            DepartmentChiefEvent.IsAddingDepartmentChief -> {
                _state.update {
                    it.copy(
                        isAddingDepartmentChief = true
                    )
                }
            }
            is DepartmentChiefEvent.SetAddingStaffSalary -> {
                _state.update {
                    it.copy(
                        addingStaffSalary = event.salary
                    )
                }
            }
            is DepartmentChiefEvent.SetAddingStaffHiringDate -> {
                _state.update {
                    it.copy(
                        addingStaffHiringDate = event.hiringDate
                    )
                }
            }
            is DepartmentChiefEvent.SetAddingStaffDepartmentID -> {
                _state.update {
                    it.copy(
                        addingStaffDepartmentID = event.departmentID
                    )
                }
            }
            is DepartmentChiefEvent.SetAddingStaffFirstName -> {
                _state.update {
                    it.copy(
                        addingStaffFirstName = event.firstName
                    )
                }
            }
            is DepartmentChiefEvent.SetAddingStaffLastName -> {
                _state.update {
                    it.copy(
                        addingStaffLastName = event.lastName
                    )
                }
            }
            is DepartmentChiefEvent.SetAddingStaffID -> {
                _state.update {
                    it.copy(
                        addingStaffID = event.staffID
                    )
                }
            }
            is DepartmentChiefEvent.SetAddingNurseManagerDoctorID -> {
                _state.update {
                    it.copy(
                        addingNurseManagerDoctorID = event.managerDoctorID
                    )
                }
            }
            is DepartmentChiefEvent.SetAddingNurseWardNumber -> {
                _state.update {
                    it.copy(
                        addingNurseWardNumber = event.wardNumber
                    )
                }
            }
            DepartmentChiefEvent.SaveNurse -> {
                if(_state.value.run {
                    addingStaffSalary <= 0 || addingStaffHiringDate.isBlank() || addingStaffFirstName.isBlank() ||
                    addingStaffLastName.isBlank() || addingStaffID <= 0
                    }) {
                    return;
                }

                try {
                    viewModelScope.launch {
                        dao.upsertStaff(
                            Staff(
                                ID = _state.value.addingStaffID,
                                hiringDate = _state.value.addingStaffHiringDate,
                                hierarchy = "Nurse"
                            )
                        )
                        dao.upsertNurse(
                            Nurse(
                                staffID = _state.value.addingStaffID,
                                hiringDate = _state.value.addingStaffHiringDate,
                                firstName = _state.value.addingStaffFirstName,
                                lastName = _state.value.addingStaffLastName,
                                salary = _state.value.addingStaffSalary,
                                departmentID = _state.value.addingStaffDepartmentID,
                                managerDoctorID = _state.value.addingNurseManagerDoctorID,
                                wardNumber = _state.value.addingNurseWardNumber
                            )
                        )
                    }
                } catch (_: SQLiteConstraintException) {

                } finally {
                    _state.update {
                        it.copy(
                            isAddingNurse = false,
                            addingStaffSalary = 0.0,
                            addingStaffHiringDate = "",
                            addingStaffDepartmentID = 0,
                            addingStaffFirstName = "",
                            addingStaffLastName = "",
                            addingStaffID = 0,
                            addingNurseManagerDoctorID = 0,
                            addingNurseWardNumber = 0
                        )
                    }
                }
            }
            is DepartmentChiefEvent.SetAddingDoctorExperience -> {
                _state.update {
                    it.copy (
                        addingDoctorExperience = event.doctorExperience
                    )
                }
            }
            DepartmentChiefEvent.SaveDoctor -> {
                if(_state.value.run {
                        addingStaffSalary <= 0 || addingStaffHiringDate.isBlank() || addingStaffFirstName.isBlank() ||
                                addingStaffLastName.isBlank() || addingStaffID <= 0 || addingDoctorExperience < 0
                    }) {
                    return;
                }

                try {
                    viewModelScope.launch {
                        dao.upsertStaff(
                            Staff(
                                ID = _state.value.addingStaffID,
                                hiringDate = _state.value.addingStaffHiringDate,
                                hierarchy = "Doctor"
                            )
                        )
                        dao.upsertDoctor(
                            Doctor(
                                staffID = _state.value.addingStaffID,
                                hiringDate = _state.value.addingStaffHiringDate,
                                firstName = _state.value.addingStaffFirstName,
                                lastName = _state.value.addingStaffLastName,
                                salary = _state.value.addingStaffSalary,
                                departmentID = _state.value.addingStaffDepartmentID,
                                doctorExperience = _state.value.addingDoctorExperience
                            )
                        )
                    }
                } catch (_: SQLiteConstraintException) {

                } finally {
                    _state.update {
                        it.copy(
                            isAddingDoctor = false,
                            addingStaffSalary = 0.0,
                            addingStaffHiringDate = "",
                            addingStaffDepartmentID = 0,
                            addingStaffFirstName = "",
                            addingStaffLastName = "",
                            addingStaffID = 0,
                            addingDoctorExperience = 0.0
                        )
                    }
                }
            }
            DepartmentChiefEvent.SaveDepartmentChief -> {
                if(_state.value.run {
                        addingStaffSalary <= 0 || addingStaffHiringDate.isBlank() || addingStaffFirstName.isBlank() ||
                                addingStaffLastName.isBlank() || addingStaffID <= 0
                    }) {
                    return;
                }

                try {
                    viewModelScope.launch {
                        dao.upsertStaff(
                            Staff(
                                ID = _state.value.addingStaffID,
                                hiringDate = _state.value.addingStaffHiringDate,
                                hierarchy = "Department chief"
                            )
                        )
                        dao.upsertDepartmentChief(
                            DepartmentChief(
                                staffID = _state.value.addingStaffID,
                                hiringDate = _state.value.addingStaffHiringDate,
                                firstName = _state.value.addingStaffFirstName,
                                lastName = _state.value.addingStaffLastName,
                                salary = _state.value.addingStaffSalary,
                                departmentID = _state.value.addingStaffDepartmentID
                            )
                        )
                    }
                } catch (_: SQLiteConstraintException) {

                } finally {
                    _state.update {
                        it.copy(
                            isAddingDepartmentChief = false,
                            addingStaffSalary = 0.0,
                            addingStaffHiringDate = "",
                            addingStaffDepartmentID = 0,
                            addingStaffFirstName = "",
                            addingStaffLastName = "",
                            addingStaffID = 0
                        )
                    }
                }
            }
        }
    }
}