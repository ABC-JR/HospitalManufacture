package com.example.hospitalinfrastructuremanagement.viewmodels.appviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalinfrastructuremanagement.room.HospitalDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppViewModel(val dao: HospitalDao): ViewModel() {
    fun getHierarchy(ID: Int): String {
        var hierarchy: String = "None"
        viewModelScope.launch {
            dao.getStaff().collectLatest {
                for (staff in it) {
                    if(staff.ID == ID) {
                        hierarchy = staff.hierarchy
                    }
                }
            }
        }
        return hierarchy
    }
}