package com.example.hospitalinfrastructuremanagement.viewmodels.nurseviewmodel

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalinfrastructuremanagement.room.HospitalDao
import com.example.hospitalinfrastructuremanagement.room.entities.Appointment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NurseViewModel(val dao: HospitalDao): ViewModel() {
    private val _appointments = dao.getAppointments().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(NurseState())

    @OptIn(ExperimentalCoroutinesApi::class)
    val state = _appointments.flatMapLatest { appointments ->
        _state.update { 
            it.copy(
                appointments = appointments
            )
        }
        _state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), NurseState())
    
    fun onEvent(event: NurseEvent) {
        when(event) {
            is NurseEvent.SetStaffSalary -> {
                _state.update {
                    it.copy(
                        staffSalary = event.salary
                    )
                }
            }
            is NurseEvent.SetStaffHiringDate -> {
                _state.update {
                    it.copy(
                        staffHiringDate = event.hiringDate
                    )
                }
            }
            is NurseEvent.SetStaffDepartmentID -> {
                _state.update {
                    it.copy(
                        staffDepartmentID = event.departmentID
                    )
                }
            }
            is NurseEvent.SetStaffFirstName -> {
                _state.update {
                    it.copy(
                        staffFirstName = event.firstName
                    )
                }
            }
            is NurseEvent.SetStaffLastName -> {
                _state.update {
                    it.copy(
                        staffLastName = event.lastName
                    )
                }
            }
            is NurseEvent.SetStaffID -> {
                _state.update {
                    it.copy(
                        staffID = event.staffID
                    )
                }
            }
            is NurseEvent.SetManagerDoctorID -> {
                _state.update {
                    it.copy(
                        managerDoctorID = event.doctorID
                    )
                }
            }
            is NurseEvent.SetWardNumber -> {
                _state.update {
                    it.copy(
                        wardNumber = event.wardNumber
                    )
                }
            }
            
            // Profile

            NurseEvent.IsAddingAppointment -> {
                _state.update {
                    it.copy(
                        isAddingAppointment = true
                    )
                }
            }
            is NurseEvent.SetAppointmentPatientID -> {
                _state.update {
                    it.copy(
                        appointmentPatientID = event.patientID
                    )
                }
            }
            is NurseEvent.SetAppointmentStaffID -> {
                _state.update {
                    it.copy(
                        appointmentStaffID = event.staffID
                    )
                }
            }
            is NurseEvent.SetAppointmentCabinetNumber -> {
                _state.update {
                    it.copy(
                        cabinetNumber = event.cabinetNumber
                    )
                }
            }
            is NurseEvent.SetAppointmentDateTime -> {
                _state.update {
                    it.copy(
                        appointmentDateTime = event.dateTime
                    )
                }
            }
            NurseEvent.SaveAppointment -> {
                if(_state.value.appointmentDateTime.isBlank()) {
                    return;
                }

                try {
                    viewModelScope.launch {
                        dao.upsertAppointment(
                            Appointment(
                                patientID = _state.value.appointmentPatientID,
                                staffID = _state.value.appointmentStaffID,
                                cabinetNumber = _state.value.cabinetNumber,
                                appointmentDateTime = _state.value.appointmentDateTime
                            )
                        )
                    }
                } catch(_: SQLiteConstraintException) {

                } finally {
                    _state.update {
                        it.copy(
                            isAddingAppointment = false,
                            appointmentPatientID = 0,
                            appointmentStaffID = 0,
                            cabinetNumber = 0,
                            appointmentDateTime = ""
                        )
                    }
                }
            }
        }
    }
}