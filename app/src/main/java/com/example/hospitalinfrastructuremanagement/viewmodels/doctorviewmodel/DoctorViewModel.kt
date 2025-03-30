package com.example.hospitalinfrastructuremanagement.viewmodels.doctorviewmodel

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalinfrastructuremanagement.room.HospitalDao
import com.example.hospitalinfrastructuremanagement.room.entities.AccidentEmergency
import com.example.hospitalinfrastructuremanagement.room.entities.Appointment
import com.example.hospitalinfrastructuremanagement.room.entities.Paediatrics
import com.example.hospitalinfrastructuremanagement.room.entities.Surgery
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DoctorViewModel(val dao: HospitalDao): ViewModel() {
    private val _appointments = dao.getAppointments().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _accidentEmergencies = dao.getAccidentEmergencies().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _surgeries = dao.getSurgeries().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _paediatrics = dao.getAllPaediatrics().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(DoctorState())
    val state = combine(_appointments, _accidentEmergencies, _surgeries, _paediatrics, _state)
    { appointments, accidentEmergencies, surgeries, paediatrics, state ->
        state.copy(
            appointments = appointments,
            accidentEmergencies = accidentEmergencies,
            surgeries = surgeries,
            allPaediatrics = paediatrics
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), DoctorState())


    fun onEvent(event: DoctorEvent) {
        when(event) {
            is DoctorEvent.SetStaffSalary -> {
                _state.update {
                    it.copy(
                        staffSalary = event.salary
                    )
                }
            }
            is DoctorEvent.SetStaffHiringDate -> {
                _state.update {
                    it.copy(
                        staffHiringDate = event.hiringDate
                    )
                }
            }
            is DoctorEvent.SetStaffDepartmentID -> {
                _state.update {
                    it.copy(
                        staffDepartmentID = event.departmentID
                    )
                }
            }
            is DoctorEvent.SetStaffFirstName -> {
                _state.update {
                    it.copy(
                        staffFirstName = event.firstName
                    )
                }
            }
            is DoctorEvent.SetStaffLastName -> {
                _state.update {
                    it.copy(
                        staffLastName = event.lastName
                    )
                }
            }
            is DoctorEvent.SetStaffID -> {
                _state.update {
                    it.copy(
                        staffID = event.staffID
                    )
                }
            }
            is DoctorEvent.SetDoctorExperience -> {
                _state.update {
                    it.copy(
                        doctorExperience = event.experience
                    )
                }
            }

            // Profile

            DoctorEvent.IsAddingAppointment -> {
                _state.update {
                    it.copy(
                        isAddingAppointment = true
                    )
                }
            }
            is DoctorEvent.SetAppointmentPatientID -> {
                _state.update {
                    it.copy(
                        appointmentPatientID = event.patientID
                    )
                }
            }
            is DoctorEvent.SetAppointmentStaffID -> {
                _state.update {
                    it.copy(
                        appointmentStaffID = event.staffID
                    )
                }
            }
            is DoctorEvent.SetAppointmentCabinetNumber -> {
                _state.update {
                    it.copy(
                        cabinetNumber = event.cabinetNumber
                    )
                }
            }
            is DoctorEvent.SetAppointmentDateTime -> {
                _state.update {
                    it.copy(
                        appointmentDateTime = event.dateTime
                    )
                }
            }
            DoctorEvent.SaveAppointment -> {
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
            DoctorEvent.IsAddingAccidentEmergency -> {
                _state.update {
                    it.copy(
                        isAddingAccidentEmergency = true
                    )
                }
            }
            DoctorEvent.IsAddingSurgery -> {
                _state.update {
                    it.copy(
                        isAddingSurgery = true
                    )
                }
            }
            DoctorEvent.IsAddingPaediatrics -> {
                _state.update {
                    it.copy(
                        isAddingPaediatrics = true
                    )
                }
            }
            is DoctorEvent.SetProcedurePatientID -> {
                _state.update {
                    it.copy(
                        procedurePatientID = event.patientID
                    )
                }
            }
            is DoctorEvent.SetProcedureStaffID -> {
                _state.update {
                    it.copy(
                        procedureStaffID = event.staffID
                    )
                }
            }
            is DoctorEvent.SetProcedureWardNumber -> {
                _state.update {
                    it.copy(
                        wardNumber = event.wardNumber
                    )
                }
            }
            is DoctorEvent.SetProcedureDateTime -> {
                _state.update {
                    it.copy(
                        procedureDateTime = event.dateTime
                    )
                }
            }

            // Procedure

            is DoctorEvent.SetEmergencyLevel -> {
                _state.update {
                    it.copy(
                        emergencyLevel = event.emergencyLevel
                    )
                }
            }
            DoctorEvent.SaveAccidentEmergency -> {
                if(_state.value.procedureDateTime.isBlank() || _state.value.emergencyLevel <= 0) {
                    return;
                }

                try{
                    viewModelScope.launch {
                        dao.upsertAccidentEmergency(
                            AccidentEmergency(
                                patientID = _state.value.procedurePatientID,
                                staffID = _state.value.procedureStaffID,
                                wardNumber = _state.value.wardNumber,
                                procedureDateTime = _state.value.procedureDateTime,
                                emergencyLevel = _state.value.emergencyLevel
                            )
                        )
                    }
                } catch (_: SQLiteConstraintException) {

                } finally {
                    _state.update {
                        it.copy(
                            isAddingAccidentEmergency = false,
                            procedurePatientID = 0,
                            procedureStaffID = 0,
                            wardNumber = 0,
                            procedureDateTime = "",
                            emergencyLevel = 0
                        )
                    }
                }
            }
            is DoctorEvent.SetSurgeryType -> {
                _state.update {
                    it.copy(
                        surgeryType = event.type
                    )
                }
            }
            DoctorEvent.SaveSurgery -> {
                if(_state.value.procedureDateTime.isBlank() || _state.value.surgeryType.isBlank()) {
                    return;
                }

                try{
                    viewModelScope.launch {
                        dao.upsertSurgery(
                            Surgery(
                                patientID = _state.value.procedurePatientID,
                                staffID = _state.value.procedureStaffID,
                                wardNumber = _state.value.wardNumber,
                                procedureDateTime = _state.value.procedureDateTime,
                                type = _state.value.surgeryType
                            )
                        )
                    }
                } catch (_: SQLiteConstraintException) {

                } finally {
                    _state.update {
                        it.copy(
                            isAddingAccidentEmergency = false,
                            procedurePatientID = 0,
                            procedureStaffID = 0,
                            wardNumber = 0,
                            procedureDateTime = "",
                            surgeryType = ""
                        )
                    }
                }
            }
            is DoctorEvent.SetAgeCategory -> {
                _state.update {
                    it.copy(
                        ageCategory = event.ageCategory
                    )
                }
            }
            DoctorEvent.SavePaediatrics -> {
                if(_state.value.procedureDateTime.isBlank() || _state.value.ageCategory.isBlank()) {
                    return;
                }

                try{
                    viewModelScope.launch {
                        dao.upsertPaediatrics(
                            Paediatrics(
                                patientID = _state.value.procedurePatientID,
                                staffID = _state.value.procedureStaffID,
                                wardNumber = _state.value.wardNumber,
                                procedureDateTime = _state.value.procedureDateTime,
                                ageCategory = _state.value.ageCategory
                            )
                        )
                    }
                } catch (_: SQLiteConstraintException) {

                } finally {
                    _state.update {
                        it.copy(
                            isAddingAccidentEmergency = false,
                            procedurePatientID = 0,
                            procedureStaffID = 0,
                            wardNumber = 0,
                            procedureDateTime = "",
                            ageCategory = ""
                        )
                    }
                }
            }
        }
    }
}
