package com.example.hospitalinfrastructuremanagement.viewmodels.departmentchiefviewmodel

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
                _state.value.staffSalary = event.salary
            }
            is DepartmentChiefEvent.SetStaffHiringDate -> {
                _state.value.staffHiringDate = event.hiringDate
            }
            is DepartmentChiefEvent.SetStaffDepartmentID -> {
                _state.value.staffDepartmentID = event.departmentID
            }
            is DepartmentChiefEvent.SetStaffFirstName -> {
                _state.value.staffFirstName = event.firstName
            }
            is DepartmentChiefEvent.SetStaffLastName -> {
                _state.value.staffLastName = event.lastName
            }
            is DepartmentChiefEvent.SetStaffID -> {
                _state.value.staffID = event.staffID
            }

            // Reusable

            DepartmentChiefEvent.IsAddingCabinet -> {
                _state.value.isAddingCabinet = true
            }
            is DepartmentChiefEvent.SetCabinetNumber -> {
                _state.value.cabinetNumber = event.roomNumber
            }
            is DepartmentChiefEvent.SetCabinetStaffID -> {
                _state.value.cabinetStaffID = event.staffID
            }
            DepartmentChiefEvent.SaveCabinet -> {

            }
        }
    }
}