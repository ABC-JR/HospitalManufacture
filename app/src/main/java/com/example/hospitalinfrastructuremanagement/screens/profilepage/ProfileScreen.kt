package com.example.hospitalinfrastructuremanagement.screens.profilepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import coil.compose.AsyncImage

@Composable
fun ProfileScreen( paddingValues: PaddingValues,navController: NavController) {
    var isEditing by remember { mutableStateOf(false) }
    val listofphoto = listOf(
        "headdoctor" to "https://kbsu.ru/wp-content/uploads/2018/09/mudunov-kbsu.jpg" ,
        "nurses" to "https://i.pinimg.com/originals/20/3c/6b/203c6b2db6d547601703d28eb1990f5d.png" ,
        "nooneboy" to "https://i.pinimg.com/originals/34/00/bc/3400bc13216a1284f7f8997cada4bb05.jpg" ,
        "noonegirl" to "https://nyaa.shikimori.one/system/user_images/original/26927/1573640.jpg" ,
        "doctorboy" to "https://microwize.com/wp-content/uploads/2015/06/shutterstock_61800133.jpg",
        "doctorgirl" to "https://avatars.mds.yandex.net/i?id=1a653e7b3692ee3404739674515d10e6_l-9221695-images-thumbs&n=13"
    )



    Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = listofphoto[4].second,
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Имя: Иван Иванов", fontSize = 20.sp)
            Text(text = "Email: ivan@example.com", fontSize = 16.sp)
            Text(text = "Телефон: +1234567890", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = { isEditing = !isEditing }) {
                    Text(if (isEditing) "Save" else "Edit")
                }
                Button(onClick = {
                    navController.navigate("signin")
                }) {
                    Text("Quit")
                }
            }
        }
    }
}
