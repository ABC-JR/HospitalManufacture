package com.example.hospitalinfrastructuremanagement.screens.mainpage

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity

import androidx.navigation.NavHostController
import coil.compose.AsyncImage


@Composable
fun MainScreen(
    padding: PaddingValues,
    navconroller: NavHostController
) {
    val context = LocalContext.current

    Column(modifier = Modifier
        .padding(padding).verticalScroll(rememberScrollState()))
    {


        // head
        Box(modifier = Modifier.height(30.dp).fillMaxWidth() ){
            Row(modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.SpaceBetween ){
                Text(text= "Hospital Manufacture" , textAlign = TextAlign.Left , fontSize = 25.sp , fontWeight = FontWeight.W800)


                Column (modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_VIEW , Uri.parse("https://2gis.kz/almaty/firm/70000001042393178"))
                    context.startActivity(intent)
                }){

                    Row{
                        Icon(Icons.Default.LocationOn , "location")
                        Text(text = "location"  , fontSize = 16.sp  , fontWeight = FontWeight.W200)
                    }
                    Text(text = "1/1 Abylai Khan Avenue, Kaskelen, Almaty region, Kazakhstan"  , fontSize = 16.sp  , fontWeight = FontWeight.W200)

                }


            }
        }
        MarqueeText("receiving online documents only during business hours 9:00 - 17:00")

        newsitem("https://avatars.mds.yandex.net/i?id=1a653e7b3692ee3404739674515d10e6_l-9221695-images-thumbs&n=13" ,
            "We have hired a wonderful new pediatrician.")
        Spacer(modifier = Modifier.padding(4.dp))
        newsitem("")

    }
}




@Composable
fun MarqueeText(text: String, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        while (true) {
            scrollState.scrollTo(0)
            scrollState.animateScrollTo(scrollState.maxValue, animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 5000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ))
        }
    }

    Row(modifier = modifier.horizontalScroll(scrollState, enabled = false)) {
        Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}




@Composable
fun newsitem(modelurl :String , text: String){
    Card(  modifier = Modifier
        .fillMaxWidth().wrapContentHeight()
        .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)){

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)  ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = modelurl ,
                "" ,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(80.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text= text)

        }



    }
}