package com.example.hospitalinfrastructuremanagement.screens.Taskpage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalinfrastructuremanagement.databases.viewmodels.DepartmentViewModel

import com.example.hospitalinfrastructuremanagement.screens.Whois


@Composable
fun Tasksforeach(paddingValues: PaddingValues, navController: NavController) {
    val whoiam = Whois.idforenter

    Box(modifier = Modifier.padding(paddingValues)){
        if(whoiam.equals("admin")){
            adminpage()
        }else if(whoiam.equals("Doctor") || whoiam.startsWith("230")){
            doctorpage()
        }else if(whoiam.equals("Nurse") || whoiam.startsWith("240")){
            nursepage()
        }else{

        }
    }
}


@Composable
fun nursepage() {
    val viewModel = hiltViewModel<DepartmentViewModel>()
    val alltasktodo by viewModel.allappointment.collectAsState()

    LazyColumn {
        itemsIndexed(alltasktodo){
            index, item ->
            MyCardDoctorsorNurse(
                "Nurse" ,
                item.firstName ,
                item.lastName ,
                item.age ,
                item.id ,
                item.type ,
                item.roomnmber ,
                item.emergencyLevel ,
                item.date ,
                item
            )

        }
    }

}





