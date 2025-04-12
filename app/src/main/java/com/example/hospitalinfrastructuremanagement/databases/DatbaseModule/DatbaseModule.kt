package com.example.hospitalinfrastructuremanagement.databases.DatbaseModule

import android.content.Context
import androidx.room.Room
import com.example.hospitalinfrastructuremanagement.databases.abstracclass.Database
import com.example.hospitalinfrastructuremanagement.databases.interfacedao.AppointmentDao
import com.example.hospitalinfrastructuremanagement.databases.interfacedao.DepartmentDao
import com.example.hospitalinfrastructuremanagement.databases.interfacedao.DoctorDao
import com.example.hospitalinfrastructuremanagement.databases.interfacedao.NurseDao
import com.example.hospitalinfrastructuremanagement.databases.interfacedao.PatientDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "database"
        ).fallbackToDestructiveMigration() // 👈 для удобства в разработке
            .build()
    }





    @Provides
    fun provideDepartmentDao(db: Database): DepartmentDao = db.db()

    @Provides
    fun provideNurseDao(db: Database): NurseDao = db.daonurse()

    @Provides
    fun provideDoctorDao(db: Database): DoctorDao = db.daodoctor()



    @Provides
    fun provideAppointmentDao(db: Database):AppointmentDao = db.daoAppointment()

    @Provides
    fun providePatientDao(db: Database):PatientDao = db.daopatient()

}
