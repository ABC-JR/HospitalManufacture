package com.example.hospitalinfrastructuremanagement.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hospitalinfrastructuremanagement.room.entities.AccidentEmergency
import com.example.hospitalinfrastructuremanagement.room.entities.Appointment
import com.example.hospitalinfrastructuremanagement.room.entities.Cabinet
import com.example.hospitalinfrastructuremanagement.room.entities.Department
import com.example.hospitalinfrastructuremanagement.room.entities.DepartmentChief
import com.example.hospitalinfrastructuremanagement.room.entities.Doctor
import com.example.hospitalinfrastructuremanagement.room.entities.Nurse
import com.example.hospitalinfrastructuremanagement.room.entities.Paediatrics
import com.example.hospitalinfrastructuremanagement.room.entities.Patient
import com.example.hospitalinfrastructuremanagement.room.entities.Room
import com.example.hospitalinfrastructuremanagement.room.entities.Staff
import com.example.hospitalinfrastructuremanagement.room.entities.Surgery
import com.example.hospitalinfrastructuremanagement.room.entities.Ward
import kotlinx.coroutines.flow.Flow

@Dao
interface HospitalDao {

    @Query("SELECT * FROM staff WHERE ID LIKE :ID LIMIT 1")
    fun getStaffByID(ID: Int): Flow<Staff>

    @Query("SELECT * FROM staff")
    fun getStaff(): Flow<List<Staff>>

    @Query("SELECT * FROM nurse WHERE ID LIKE :ID LIMIT 1")
    fun getNurseByID(ID: Int): Flow<Nurse>

    @Query("SELECT * FROM nurse")
    fun getNurses(): Flow<List<Nurse>>

    @Query("SELECT * FROM doctor WHERE ID LIKE :ID LIMIT 1")
    fun getDoctorByID(ID: Int): Flow<Doctor>

    @Query("SELECT * FROM doctor")
    fun getDoctors(): Flow<List<Doctor>>

    @Query("SELECT * FROM department_chief WHERE ID LIKE :ID LIMIT 1")
    fun getDepartmentChiefByID(ID: Int): Flow<DepartmentChief>

    @Query("SELECT * FROM department_chief")
    fun getDepartmentChiefs(): Flow<List<DepartmentChief>>

    @Query("SELECT * FROM nurse WHERE salary > :salary")
    fun getNursesWithSalaryMoreThan(salary: Double): Flow<List<Nurse>>

    @Query("SELECT * FROM doctor WHERE salary > :salary")
    fun getDoctorsWithSalaryMoreThan(salary: Double): Flow<List<Doctor>>

    @Query("SELECT * FROM department_chief WHERE salary > :salary")
    fun getDepartmentChiefsWithSalaryMoreThan(salary: Double): Flow<List<DepartmentChief>>

    @Upsert
    suspend fun upsertStaff(staff: Staff)

    @Upsert
    suspend fun upsertNurse(nurse: Nurse)

    @Upsert
    suspend fun upsertDoctor(doctor: Doctor)

    @Upsert
    suspend fun upsertDepartmentChief(departmentChief: DepartmentChief)

    // Staff, Nurse, Doctor, Department chief, Department

    @Query("SELECT * FROM department WHERE department_id LIKE :departmentID LIMIT 1")
    fun getDepartment(departmentID: Int) : Flow<Department>

    @Upsert
    suspend fun upsertDepartment(department: Department)

    // Department

    @Query("SELECT * FROM patient WHERE ID LIKE :ID LIMIT 1")
    fun getPatient(ID: Int): Flow<Patient>

    @Upsert
    suspend fun upsertPatient(patient: Patient)

    // Patient

    @Query("SELECT * FROM room WHERE room_number LIKE :roomNumber LIMIT 1")
    fun getRoomByNumber(roomNumber: Int): Flow<Room>

    @Query("SELECT * FROM room")
    fun getRooms(): Flow<List<Room>>

    @Query("SELECT * FROM ward WHERE room_number LIKE :roomNumber LIMIT 1")
    fun getWardByNumber(roomNumber: Int): Flow<Ward>

    @Query("SELECT * FROM ward")
    fun getWards(): Flow<List<Ward>>

    @Query("SELECT * FROM cabinet WHERE room_number LIKE :roomNumber LIMIT 1")
    fun getCabinetByNumber(roomNumber: Int): Flow<Cabinet>

    @Query("SELECT * FROM cabinet")
    fun getCabinets(): Flow<List<Cabinet>>

    @Upsert
    suspend fun upsertRoom(room: Room)

    @Upsert
    suspend fun upsertWard(ward: Ward)

    @Upsert
    suspend fun upsertCabinet(cabinet: Cabinet)

    // Room, Ward, Cabinet

    @Query("SELECT * FROM appointment")
    fun getAppointments(): Flow<List<Appointment>>

    @Query("SELECT * FROM appointment WHERE staff_id LIKE :staffID AND patient_id LIKE :patientID AND cabinet_number LIKE :cabinetNumber LIMIT 1")
    fun getAppointment(staffID: Int, patientID: Int, cabinetNumber: Int): Flow<Appointment>

    @Upsert
    suspend fun upsertAppointment(appointment: Appointment)

    // Appointment

    @Query("SELECT * FROM `accident&emergency`")
    fun getAccidentEmergencies(): Flow<List<AccidentEmergency>>

    @Query("SELECT * FROM `accident&emergency` WHERE staff_id LIKE :staffID AND patient_id LIKE :patientID AND ward_number LIKE :wardNumber LIMIT 1")
    fun getAccidentEmergency(patientID: Int, staffID: Int, wardNumber: Int) : Flow<AccidentEmergency>

    @Query("SELECT * FROM surgery")
    fun getSurgeries(): Flow<List<Surgery>>

    @Query("SELECT * FROM surgery WHERE staff_id LIKE :staffID AND patient_id LIKE :patientID AND ward_number LIKE :wardNumber LIMIT 1")
    fun getSurgery(patientID: Int, staffID: Int, wardNumber: Int) : Flow<Surgery>

    @Query("SELECT * FROM paediatrics")
    fun getAllPaediatrics(): Flow<List<Paediatrics>>

    @Query("SELECT * FROM paediatrics WHERE staff_id LIKE :staffID AND patient_id LIKE :patientID AND ward_number LIKE :wardNumber LIMIT 1")
    fun getPaediatrics(patientID: Int, staffID: Int, wardNumber: Int) : Flow<Paediatrics>

    @Upsert
    suspend fun upsertAccidentEmergency(accidentEmergency: AccidentEmergency)

    @Upsert
    suspend fun upsertSurgery(surgery: Surgery)

    @Upsert
    suspend fun upsertPaediatrics(paediatrics: Paediatrics)

    // Accident&Emergency, Surgery, Paediatrics
}