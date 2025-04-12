package com.example.hospitalinfrastructuremanagement.screens.profilepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import coil.compose.AsyncImage
import com.example.hospitalinfrastructuremanagement.databases.viewmodels.DepartmentViewModel
import com.example.hospitalinfrastructuremanagement.screens.Whois





@Composable
fun MainProfileScreen(navController: NavController, paddingValues: PaddingValues) {
    val user = Whois.idforenter
    if (user.equals("admin")) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            item { AdminPage() }
            item { ProfileScreen(navController) }
        }
    } else {
        ProfileScreen(navController)
    }
}

@Composable
fun ProfileScreen(

    navController: NavController
) {
    val user = Whois.idforenter

        val lastname = Whois.lastname
        val firstname = Whois.firstname
        val salary = Whois.salary
        val hiringDate = Whois.hiringdate



        val photos = listOf(
            "headdoctor" to "https://kbsu.ru/wp-content/uploads/2018/09/mudunov-kbsu.jpg",
            "nurses" to "https://i.pinimg.com/originals/20/3c/6b/203c6b2db6d547601703d28eb1990f5d.png",
            "nooneboy" to "https://i.pinimg.com/originals/34/00/bc/3400bc13216a1284f7f8997cada4bb05.jpg",
            "noonegirl" to "https://nyaa.shikimori.one/system/user_images/original/26927/1573640.jpg",
            "doctorboy" to "https://microwize.com/wp-content/uploads/2015/06/shutterstock_61800133.jpg",
            "doctorgirl" to "https://avatars.mds.yandex.net/i?id=1a653e7b3692ee3404739674515d10e6_l-9221695-images-thumbs&n=13"
        )

        val (roleName, imageUrl) = when (user) {
            "Nurse" -> "Nurse" to photos[1].second
            "Doctor" -> "Doctor" to photos[4].second
            "admin" -> "Department Chief" to photos[0].second
            else -> user to photos[3].second
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFE3F2FD), Color(0xFFFFFFFF))
                    )
                ),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        AsyncImage(
                            model = imageUrl,
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .size(130.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                        Text(roleName, style = MaterialTheme.typography.headlineSmall)
                        Spacer(modifier = Modifier.height(8.dp))
                        Divider(modifier = Modifier.padding(vertical = 8.dp))

                        ProfileItem("First Name", firstname)
                        ProfileItem("Last Name", lastname)
                        ProfileItem("Salary", salary)
                        ProfileItem("Hiring Date", hiringDate)

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            OutlinedButton(
                                onClick = { navController.navigate("signin") },
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Icon(Icons.Default.ExitToApp, contentDescription = null)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Quit")
                            }
                        }
                    }
                }
            }
        }


}



@Composable
fun AdminPage() {
    val viewModel = hiltViewModel<DepartmentViewModel>()


    val maxAgePatient by viewModel.getMaxAgePatient().collectAsState(initial = "...")
    val minAgePatient by viewModel.getMinAgePatient().collectAsState(initial = "...")
    val notAppointPatient by viewModel.getPatientWithoutAppointment().collectAsState(initial = "...")

    val countNurses by viewModel.getCountNurses().collectAsState(initial = "...")
    val salaryNurses by viewModel.getAllSalaryNurses().collectAsState(initial = "...")
    val avgSalNurses by viewModel.getAVGSalaryNurses().collectAsState(initial = "...")
    val minSalaryNurses by viewModel.getMinSalaryNurses().collectAsState(initial = "...")
    val maxSalaryNurses by viewModel.getMaxSalaryNurses().collectAsState(initial = "...")

    val countDc by viewModel.getAllDoctorsCount().collectAsState(initial = "...")
    val salaryDc by viewModel.getAllSalaryDoctors().collectAsState(initial = "...")
    val avgSalDc by viewModel.getAVGSalaryDoctor().collectAsState(initial = "...")
    val minSalaryDc by viewModel.getMinSalaryDoctor().collectAsState(initial = "...")
    val maxSalaryDc by viewModel.getMaxSalaryDoctor().collectAsState(initial = "...")

    Column (modifier = Modifier.padding(16.dp)) {

        Text("Patients", style = MaterialTheme.typography.titleLarge)
        InfoCard("Max. Age of Patient", maxAgePatient)
        InfoCard("Min. Age of Patient", minAgePatient)
//InfoCard("Patients Without Appointment", notAppointPatient)

        Spacer(modifier = Modifier.height(16.dp))
        Text("Nurses", style = MaterialTheme.typography.titleLarge)
        InfoCard("Number of Nurses", countNurses.toString())
        InfoCard("Total Salary of Nurses", salaryNurses.toString())
        InfoCard("Average Salary of Nurses", avgSalNurses.toString())
        InfoCard("Min. Salary of Nurse", minSalaryNurses)
        InfoCard("Max. Salary of Nurse", maxSalaryNurses)

        Spacer(modifier = Modifier.height(16.dp))
        Text("Doctors", style = MaterialTheme.typography.titleLarge)
        InfoCard("Number of Doctors", countDc.toString())
        InfoCard("Total Salary of Doctors", salaryDc.toString())
        InfoCard("Average Salary of Doctors", avgSalDc.toString())
        InfoCard("Min. Salary of Doctor", minSalaryDc)
        InfoCard("Max. Salary of Doctor", maxSalaryDc)


    }
}

@Composable
fun InfoCard(label: String, value: String?) {  // Notice the String? instead of String
    if (value != null) {  // You can check for null value safely
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = label, style = MaterialTheme.typography.bodyLarge)
                Text(text = value, style = MaterialTheme.typography.bodyLarge)
            }
        }
    } else {
        // Provide a fallback UI in case value is null
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = label, style = MaterialTheme.typography.bodyLarge)
                Text(text = "N/A", style = MaterialTheme.typography.bodyLarge)  // Default value for null
            }
        }
    }
}


@Composable
fun ProfileItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 16.sp, color = Color.Gray)
        Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Medium)
    }
}
