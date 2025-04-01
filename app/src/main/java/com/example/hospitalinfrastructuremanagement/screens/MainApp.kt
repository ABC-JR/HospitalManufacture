package com.example.hospitalinfrastructuremanagement.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.hospitalinfrastructuremanagement.screens.Taskpage.Tasksforeach
import com.example.hospitalinfrastructuremanagement.screens.mainpage.MainScreen
import com.example.hospitalinfrastructuremanagement.screens.profilepage.ProfileScreen
import com.example.hospitalinfrastructuremanagement.screens.signinpage.Signscreen
import com.example.hospitalinfrastructuremanagement.viewmodels.appviewmodel.AppViewModel
import com.example.hospitalinfrastructuremanagement.viewmodels.departmentchiefviewmodel.DepartmentChiefViewModel
import com.example.hospitalinfrastructuremanagement.viewmodels.doctorviewmodel.DoctorViewModel
import com.example.hospitalinfrastructuremanagement.viewmodels.nurseviewmodel.NurseViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainApp(
    departmentChiefViewModel: DepartmentChiefViewModel,
    doctorViewModel: DoctorViewModel,
    nurseViewModel: NurseViewModel,
    appViewModel: AppViewModel

) {
    val navconroller = rememberNavController()
    val navBackStackEntry by navconroller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBars = currentRoute != "signin"
    var namefortasks by remember { mutableStateOf("")}
    var idfortasks by remember { mutableStateOf("")}


    Scaffold(
        topBar ={
            if(showBars){
                MyTopAppBar()
            }

        } ,
        bottomBar = {
            if(showBars){
                BottomBar(navconroller , namefortasks , idfortasks)
            }

        }
    )
    {
        padding->

        val isUserLoggedIn = false

        val destination = if (isUserLoggedIn) "mainpage" else "signin"

        NavHost(navconroller , startDestination = destination) {
            composable("signin") {
                Signscreen(navconroller , appViewModel = appViewModel , context = LocalContext.current)
            }
            composable("mainpage") {
                MainScreen(padding ,  navconroller)
            }
            composable("tasks/{whois2}") {
                val name1  = it.arguments?.getString("whois2") ?:""
                Tasksforeach(padding , navconroller ,name1)
            }
            composable("profile/{whois}/{id}") {

                val name = it.arguments?.getString("whois") ?:""
                val id = it.arguments?.getInt("id") ?: ""
                namefortasks = name
                idfortasks =id.toString()

                ProfileScreen(
                    padding, navconroller, name,  id.toString()
                )
            }
        }

    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    TopAppBar(
        title = { Text("Hospital") },
        navigationIcon = {
            AsyncImage(
                model = "https://i.pinimg.com/1200x/2e/02/bf/2e02bf2de46b5cbddf3cfbc16e83e822.jpg",
                contentDescription = "logo",
                modifier = Modifier.size(50.dp)
            )
        }
    )
}




data class triple(
    val name:String,
    val route:String,
    val id: ImageVector
)

@Composable
fun BottomBar(navconroller: NavController, name: String, idfortasks: String){
    val list = listOf(

        triple("Hospital" , "mainpage" , Icons.Default.Add) ,
        triple("Tasks" , "tasks" ,Icons.Default.List ) ,
        triple("Profile" , "profile" , Icons.Default.Person)
    )

    var selectedid by remember{ mutableStateOf(0)}

    NavigationBar{
        list.forEachIndexed{
            index , item->
            NavigationBarItem(
                selected = selectedid ==index ,
                onClick = {
                    selectedid = index
                    when(item.route){
                        "tasks"->navconroller.navigate("tasks/${name}")
                        "profile"->navconroller.navigate("profile/${name}/${idfortasks}")
                        "mainpage" ->  navconroller.navigate(item.route)
                    }

                } ,
                icon = {
                    Icon(item.id , "")
                } ,
                label = {
                    Text(text =item.name)
                }
            )
        }
    }
}