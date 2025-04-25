package com.example.hospitalinfrastructuremanagement.screens.signinpage


import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.hospitalinfrastructuremanagement.databases.Entities.Department
import com.example.hospitalinfrastructuremanagement.databases.Entities.Doctor
import com.example.hospitalinfrastructuremanagement.databases.viewmodels.DepartmentViewModel
import com.example.hospitalinfrastructuremanagement.screens.Whois


@SuppressLint("SuspiciousIndentation")
@Composable
fun Signscreen(
    navController: NavController,
    context: Context,

) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val viewmodel = hiltViewModel<DepartmentViewModel>()

    LaunchedEffect(Unit) {
        viewmodel.insertItem(Department(departmentname = "Nurse"))
        viewmodel.insertItem(Department(departmentname = "Doctor"))
        viewmodel.insertItem(Department(departmentname = "admin"))


        for(i in 0 .. 100){
            viewmodel.insertDoctor(Doctor(
                hiringdate = "23",
                salary = "6500",
                lastname = "lastname",
                firstname = "firstname",
                idforenter = "230${i}"
            ))
        }

    }




    var listofadmin  = viewmodel.allworkers.collectAsState()
    var listofdoctors = viewmodel.alldoctor.collectAsState()
    var listofnurses = viewmodel.allnurses.collectAsState()


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(
                    model = "https://i.pinimg.com/1200x/2e/02/bf/2e02bf2de46b5cbddf3cfbc16e83e822.jpg",
                    contentDescription = "logo",
                    contentScale = ContentScale.Fit ,
                    modifier = Modifier.size(100.dp).clip(RoundedCornerShape(12.dp))
                )

                Spacer(modifier = Modifier.padding(10.dp))
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Sign In",
                            style = MaterialTheme.typography.headlineMedium,
                            fontSize = 24.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("id") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth() ,
//                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = {
                                val matched = listofadmin.value.any {
                                    if(it.departmentname == email){
                                        Whois.idforenter = email
                                    }
                                    it.departmentname == email
                                }
                                val matched2 = listofdoctors.value.any{

                                    if (it.idforenter == email) {
                                        Whois.idforenter = it.idforenter
                                        Whois.salary = it.salary
                                        Whois.hiringdate = it.hiringdate
                                        Whois.lastname = it.lastname
                                        Whois.firstname = it.firstname
                                        navController.navigate("profile")
                                        true // <-- return true from lambda
                                    } else {
                                        false // <-- return false if not matched
                                    }


                                }
                                val matched3 = listofnurses.value.any{
                                    if (it.idforenter == email) {
                                        Whois.idforenter = it.idforenter
                                        Whois.salary = it.salary
                                        Whois.hiringdate = it.hiringdate
                                        Whois.lastname = it.lastname
                                        Whois.firstname = it.firstname
                                        navController.navigate("profile")
                                        true
                                    } else {
                                        false
                                    }


                                }
                                    if(matched){
                                        Whois.idforenter = email
                                        Whois.salary = ""
                                        Whois.hiringdate = ""
                                        Whois.lastname =""
                                        Whois.firstname = ""

                                        navController.navigate("profile")
                                    }else{
                                        Toast.makeText(context , "You sign in first" , Toast.LENGTH_LONG).show()
                                    }
                                      },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.Blue
                            )
                            ,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text("Sign In", fontSize = 18.sp)
                        }
                    }
                }

            }

        }

}

