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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.text.font.FontStyle
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
        Box(modifier = Modifier.wrapContentHeight().fillMaxWidth() ){
            Column {
                Text(text= "Hospital Manufacture" ,
                    modifier = Modifier.padding(4.dp),
                    textAlign = TextAlign.Left , fontSize = 25.sp , fontWeight = FontWeight.W800)
                Row(modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.SpaceBetween ){
                    Row{
                        Icon(Icons.Default.LocationOn , "location")
                        Text(text = "1/1 Abylai Khan Avenue Kaskelen Almaty region Kazakhstan"  , fontSize = 16.sp  , fontWeight = FontWeight.W200)
                    }
                }
                  MarqueeText("receiving online documents only during business hours 9:00 - 17:00")

            }

        }
        newsitem("https://avatars.mds.yandex.net/i?id=1a653e7b3692ee3404739674515d10e6_l-9221695-images-thumbs&n=13" , "We have hired a wonderful new pediatrician." , "")
        Spacer(modifier = Modifier.padding(4.dp))
        newsitem("https://img.inform.kz/kazinform-photobank/media/2025-02-19/3f4d8b70-319b-4316-a770-bda9b6e4278c.webp" ,
            "The list of the best medical institutions in the country includes two Karaganda hospitals" ,
                "https://www.selectyouruniversity.com/blog/top-medical-universities-in-kazakhstan"
            )
        Spacer(modifier = Modifier.padding(4.dp))
        newsitem("https://kaztag.kz/upload/resize_cache/iblock/2b7/881_500_2/IMG_8732.jpg?174437053267589" ,
            "A hospital is being built in Kokshetau as part of a partnership between Kazakhstan and France." ,
            "https://kaztag.kz/ru/news/v-kokshetau-stroitsya-bolnitsa-v-ramkakh-partnerstva-kazakhstana-i-frantsii")
        Spacer(modifier = Modifier.padding(4.dp))
        newsitem("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxASEhUTERIVFRUWFhUYGBgXGBcgGBgYGxcfGBUYGBcYHyghGBolHRcYITEiJSorLi4uGB8zODMsNygtLisBCgoKDg0OGxAQGy0lHyYtLS0tKy0uLS0tLS0tLS8tLS0tLS0wLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLf/AABEIALwBDAMBIgACEQEDEQH/xAAbAAABBQEBAAAAAAAAAAAAAAACAQMEBQYAB//EAE0QAAEDAgQDBAUGDAUCBAcAAAECAxEAIQQFEjEGE0EiMlFhI0JxgZEUM1JiodEHFRYkQ1NykrHB0vBjgoOT4TTCVdPi8RdERVRzdLL/xAAaAQADAQEBAQAAAAAAAAAAAAAAAQIDBAUG/8QAMREAAgIBAgUCBQIGAwAAAAAAAAECEQMSIQQTMUFRkaEUImFxgcHwI0JS0eHxBTKx/9oADAMBAAIRAxEAPwCwCaMJogmjCa+n1Hz1ABNEE0YFEBU2VQATShNHFLppWFABNLpowml00WOgIpYo4pYpWOgIroo4pYosAIroo4pYosAIroo4roosAIroo4roosAIpYo4roosAIroo4roosBuK6KciuiiwG4ropyK6KdjG4pIpyK6KLENaaQpp2KQinYDWmhKaeIoSmnYqGSmh00+U0MU7FQITRBNEBRAVjqHQIFEBRAUoFKx0DFKBRRSxSsdAxSxRRSxRYUDFdFFFLFFjoGK6KKKWKVgBFLFFFdFFgDFdFHFdFFgBFdFHFdFFgDFdFFFLFFgBFdFHFdFFgBFdFFFdFOwBikijiuiiwAiuijikiiwAiuijiuinYDUUhFOxSRTsVDRFCRTpFJFOwoACiArgKICsNQ6EAoopQKUClqHQkUsUtLFFhQMUsUUV0UrHQkUsUsUsUWFAxXRRRXRRYUDFLFFFdFFhQMUsUUV0UagoGK6KKKWKLCgIroooroosKBiuiiiuiiwoGK6KKK6nYUDFdFFFdFFhQMUkUVdFFhQMUkUcUkU9QUDFCRR0hFPUFAEUkUcUkUahUNiioBRCsLLoIUopKUUWOhaKkFLSsKOpa6losKEpa6losKOrqi4/MmWdPNXp1Tp7KjMRPdB+kKgq4mwUj0ptf5tfn5e2k5pCbS7lxSxVMviVmUhKHlpVspDLhE6tMEjaCk/EVal4ysaT2PGbiJJFr0a0PYciqjNM9Q0SlEKUNye6PH21KzLFENq0SVbCL77m1ZjD4Qq1akKg6ROlyEyZk+4AUattjnzZHF6Uc/xDiDsoDe2keE/d8aNnid4ROlQt08RqgEdYn4edO5rgWwQGzqkK2LpkqHiJA8ImfKob7LpQljlkKErEhc6rQL+Saycu6ZnrmnTTNTlObtvjsmFASUnw8QeovVhXmmFafS6lTKFagq1lARuASTEQVD3AVuMTnbaEpVpWrUJhI7thY+G/wBhrTWkrZvjnqW5Z0lQMFmoccDfKcQSkrGsAApiZ36xVjFOORS6GtA11ckggEbESKWqsKErqWuosKBrqWuosKBrqWkp2FCUlLSU7ChDSUppKLFQwKIVV5W6/wBrn6SSoRpJgCIi+23SrMKFc0MsZq4s1lBx2Yc0ooAaMGrsVBCqTPc1cadbQhTaQsGVOBUDfcjYWq3dfQi61pSPrKA/j7DWb4hzLDpeQVpQ8C2QI0KCTMzfyBHvqJyenYibpEVfE+ICUK1snUTYJVKYHrCbT09ho/ykxEpHNY7SUGdJhGokEK8CIvvVY5meHOqGB2j2Oy32JBO/W1SPxzg9U/JRA1W0txBEIHnBBN/GsNcjG35JSeJcR2/StDTtKD27xCfHxvFhTLnEL/Lu42oKDgKdEqF4OqfGT18aZczTCQv83upQ0wEdkaQCPKVBRkfSpG83wmhA+SyU6dR0N9rSfSfvX3o1sLdAZniFKVpK0LCCCChJCe1pJj3kD3VBBT5beB+t51MzDHMuaeW3oUkkrIQkapUnTZPgDFQRiEzHl9BP1vOlZDTbHmlJg2m6/L1T0oVEfR6K6/4Yo0ZyHUKWToJURCbAQhYE36gST41Mw2ODfKcOpULcMTuAy3aff9tVexk4NPcrlOJvbqOv7NNa02MeHX/ErUucUt623OQSEJWkplPaKiiFG3SoQ4ibOIDxZOnlhHLkROuNW0T7qm2WkZ1DwjY9w+t9Y0CXBffYet/hnyq3az5GhKS0slCuYVHTKkgqlHs+yjxfEbawtIZUNSSAezCZbkEeylZtTXQoC9f1v3v8P2U2rEXN1dfX/wAQeVWxzpHK5fKVq0KTrtM8udXj9tOKz9mw5KrC4hN4cgnegtOVdClTiTPeX+//AIseFWOF4rxKG+ShxOkSLzquQe+IPrH+xTyc5YtLSvV6J/WEeNY3EYkBZmTB36/x/uKpOu5eLVO7ibvCcWYxBDQcw5CYAUZKY0z3yb+A9wpDxpjFo1amUxpsAoKOoxYTJA6+FqzmW49hRGtN4FgLE7Tv4RUov4XQAEEnoTNkyNNgd/7vV635Kdp1pZfI42xaUpnkKmfpFVjHaAVY+3pFIvi3GFwiWpANgrsQIJhU9ryuZ261RIdwxKeybEat72GrrSoVh4nSYKExZUAyPPwilrfkL+jLzCcZ4pCCrS2sFZHaUoqBjYX1Rbe9IeOHytK9DYi0AqggiYIP92qjeSzB0jquJmdj5+NQURbb1fH6Jo5j8lJWj1jhbOVYtkuKQlJCtMJJI7qVdf2quKyv4No+SGP1h8foI8a1RrojK0S0JSUtE22Vd0E9bVVhQ3SU9yF/RV8DUd7mJMch1XmkCPtqZZYR6sahJ9EZt/PH0KCHMK2CqdKfkzxUbjwJHUfGouPzrEOrGGTg28OHSAla2nUqBnoYm5AEgdayZxWMG2KxH6H1n/oX+NPOPZgkjViMSntPRq+Ui2kREjYGuCLiuxtJtmsz7OFqwjPK5QxJxDra0MAaiEFaR2DK47INxWe5uY/q8R/tK/pqv+U4/Tq5+I7g7UYjvcyJnTv08aNzMseNUYp/dvriPomfVpaq7svUvBJc/GKpCkYggXALSyJ9mnz+2kw6Mem4afB8mVD/ALaFWY5iFJnFPgF1W/yi6ZTAujb7KbazfMYT+dvHsuT8/cwY9T2Vby3GiKjd0XLBxXJKVMqJVqupDusewBuEm46/ClOcvlSVHL3BpTsELuT/AKfqxb21THOcxAvi3R6Ob87fXv3PCkczzMYVGMc7rcfObkCfU9tRaIcIt9C5azZ1JX+YOnWsG6FdkaAmPm9pTq9qqk4vMMM3h2QVI54KQWyQCdPeClFNiYEzYTvWdfz7MQV/ni7LSB3rDtSLo9lArNcaVAnEAkPEAkCdJNxJRRaBYolxxZgsSC0leGWhQhyW9DmtKlAIUS3YRBF+gqj5b83S/t+rMev/AH8Kew/EeYp5Y+VkDWUgSkdgadKQNNgJNvOn2OJMx5AUcWSrmOCZRsGQoDboq9FlaEDjMQt4qWsFpSp9HyyTZCkpN0zcA/GKsMryfGqUiW1qaldiydi2Egxo8QPhVOzxJmCSCMUkKLayT6GSQVwZ0+Q+FSE8X5rb88/Rk7sbyb7eylqI5SNXl/C2J52pxgKa1uHTKEnSdPL6yIIP2VN/JBzmhQZa0aAOWpdtUyV6gkn3ViF8Y5oAfzsfNpO+H3JTJ28z8aBzjTNAFRixZts74fc6JO3mfjTv7i5EW7Nw5wY6QBy8OIN4Ubi8pPYiDPS9Nv8ABbhSoJYaSSISUqB0nTpBgxPvrGuca5oAqMWLJQRfDbnTPTzPxrsRxtmo5kYoWKI/6fqDPSla+o+Si8xPB2MSmOQlRhQ1dZ0wkhKAr4edNYvK0NAcxgiwnsKBnXJgETtVPieOM1GuMUmy0gf9PtCp6eQo3ON81lY+UpgOhP8A8v3Tqtt5Cnq+ongXYE4jBggKTpVaxbcB756EeMVisQ8jUZCd+gttWsVxNjSqS4zPN0zpwwMe4b/bU1zi3MByAH09tKtV2bw8tIj/ACpAt4U0y8ePR/sw7WMSCCIkR0ET7K22S4Bl/ChxJQFlagEq0CEgxIUTMSPDpTbHGmZHRL6L65+Z6C1AOKMYpOpSmitSFqJhj1dQSSf8op6kXJeCO4+0hehSE6kqAVASQYCZuDeZFAMxY6JTBTAGlNlSCIv4EVORxnmQAAebADZMDkxOoj4Vy+N8zhXpm7IbI+Z3OmevmaNSFoKx/MWbpAg61QYTaxEb+N69L4T4KwT+Dw7rjSitbSFE6lCTG8AwKw7/ABrmY1w63Yoj5nqDPXyp8cb5mFaQ8iOdo/Rd2fbvWWROS+WVG2Nxj1VnrOV8NYbDoKGkqSmZjWreAPHwAqX+Km/rfvK++vFm+O80ITL6LhyfmfVTI6+NcnjzNInno+bKv0O+sjx2ih62qU2hpxT3ij2HMcsTy1d4W/iRvPT76pVZgrCpUtTo0FSQJSnsgkJCd7jy8684d49zSFenRYNdGfWTJ+2l/LjMtUc1uOapPdZ7oIj31eNySalKyJpN3FUbnGcYMEgqxbaYPgBcdCA4AabYz98ypDiVJWdQPaIIIER24AgCwrz9njHHCVhTAUpsqUoIw8k69NzuRAHwqFjeJcYVkksknST82LlIJsFCtIygupOmfkiKS0QR27hj6P0LVb51mjOIWkhLiNKsTuUquUgnwgVuV8LYKY5Cbqjvq2SCE/CKiYvI8vbKAWCdYeOrUrskNlS1EAncJ2rC6RzriIy6GKVmDPyXk+knlC8JiPlOvbVv0qDiOXC+0vdn1U/QP1q32C4awL7iS0AWFNgAQ4Ce30cKh61+576ZwmT5c9in8MGHAppQ1K5hg6bJi8iAabvuPnRKHPM0ZeW1pLo0vuzqSndRbsIXt2ftqry/ENoLait2Ah/ZKZuhYn5zcTXoiODcERq0LkFS+8R2rSYnawtSucC4FKSSlwBCFHvzYg6vaYmht2L4iFmLyvOm2H235dd5bAELSAV30ye2qDfz2qJneYIedfd1LRr5atIEhMhNgdQn7K3bHBOAWjUnnaYCB2h3ZB28ZNRUcKZcvEKwv5xr0BRunToT2RB8bD7aTGssG2zM5VmjbKnipx67jQ7CRIjXa7gsf5VCddBcJDyxOJUdldVbb1uX+BMJclTo1OXkp3GqIge3ekRwLhiodp7vFzdG9vsph8RAyWTZilAbBxDo9OtRCQohY0JGlUrEAG+x3qNhXT8mT6c/OvX9J+oFv51o8v4dyxwJLWJcVCzEQe3CSQYTYd341LxPBTDWHUA45CC4u8bqSlszpBMQZsDenTY3ngtmZzC5mgYYNnEK1Fl8bOQSVLhU+X8qbyHHIbxDS3niptKJWkcwkiTNiINSk8Nt6AsLTAac9dcxqWDblVyOHEFWlK78o7qULXm/K8qemQp5cclpb6jPE2YNOYh5eHeKGlNtlCfSCB6MGQAQLz8ai5djQhayvEW5DYHzpgy2ZjT1APxq4PCKIc1OXS2gGFE9EqF+V5D7qiv8NN+kBcA0obSSVkC2gdWvGKlW3Q45McUlZXZtjCpx9SH4SQgj5wR3emm3/NW2f5nhVYJDbThTiEOI5qocAKdCxAUAZuU9OlRcVkLaS4Cu8Ng9v9n/AA6lnhELJHMA1rbFlyRIJHZDc7SY8qbTS3E545NO+hRqxziFOEPIPpEiFBwjZfTTSO4hepfph8+P1niu3dq4xHC6SXAFj50brAvLiRujextemXMiRqV2/wBN9PqCqR3KEmynmxrcXOsxQ4Uct9NsQ5MJcHZOjSnuCYhXleoLrypw3ph3F/Tv6d36vl9lT0cOJ1pGsSXln5wGCmCoGEez41dYjgdA5R5y/Rsz3TfUtxzw+vE+VOmEuIxx6sxuGxSxyxzxBS7NlXsY9SpWT4vSttS30wGnpssiYcCTGjoYPuq4HB7SdAL5kIWUgi5sqYGmnW+C24+eV2W1C6bQdRJJjpqPwqdwefGUmb4sLcKm3k6eQi4CwCQAFGCgdZqE7iF6VemTZtq/b6hP1a1f5EtkSHlEaEpsm0Egzt50DnBTcKHOVcIT3T6oF9vq/bQxfEY/JmcU+rS6OcJ5jcd+w0rn1fGPhVtmuYtOBkNaW1pfUly6zruNJ7lutvtqY/we0VLTzld8TA2jVFo86e/JFrVPOJPNUogCYUCJTt509xSy4m032Mzl+JhTRU8Ih0kws+pvGm8b0uOxRMEPpP5uiSAsAq5kExoH8KulcLYdJQgvqBKHSJSdilWok6bQAaUcL4ckoS+okNJ2SY0lwXnT4mKRfxEKv9CiYfAWC46NAXhtca506e2O7uRNOM4iH0kujSMQskQvugpMRp8Ku3eE2b6nlAFxpMlJ3CSBEJ3MVyOFWyoEOKnW6r3wPq7Wp7i+Jx9bKTMcWF6Sh4H83gmFiVc1UmNPgQPdVZjXVaz6UbJ+n9EfVrUp4WaCbOqPogNvFyeqfOue4QZUokuL8Nh0EeHlTaZL43DF7siuIzI3IfUYZvcnu9u9+s08vEZmTcYgwp/1FGxRA9XrJHnJr0PiXhDAfJn3EspQpLLqgUkgSEEjs7RbwrO8EZGhsPvhYM4FR06TstJvqP7O38KNPY12ujONZhmjaAlHyhIDQgBo2PN6dnw6UAx2YNrccQHUrWWtSg0NSgUkqk6L3ApvivJH8G4hClp9I2tQKVK9ngIMmrBWV4pCApSX0gpBB7cQRIuLUaWxuMUAniHMwoDmOAF5aT6JHdlNu55m9J+VOZQkFxR1Id1S03eEqiZRUj8GWJWpx0LcUv8ANcSoSpRiNIBubG9UeZYx9Jbh90STPpF+APjScX1J5cW+haN8W49KIDgENhUctuNWsDbT4VFf4nxOtTxKOYlDcK0JHeAJBAsbk70eFxb6kgh96D/iOffVl+DnGuuY5tDrzjgKnuypaykhLa9wTG4B91GhvqChFdiqHEr6C5BSfSIEL1KA7+wUqx9lSMTxdinFJClJEPFA06k9mRvpUJ261EzjMsUmNL7olyO+ra9t6dwGY4koBLyyZN5m4MdaKZXLj1GMmz5xoIS2hkAuq9VRjsouO1VseNcU5hiVBo61OoV2TEJaS4PW31fZUPhvMX145ltxzU2rEpSUkC6ZgiwrZZq2EYfGqSEhTeMCG+ynsIKRKQIjqf7FCsUsUW90YdviZ7lhOhmCy70XPeXbv7Wpxvil4K1aGZ5JNwuL26L86hYTNcQpQlxJEKEctr+j21JxGPeCSdSbAfomfHzRRchPBDwiSeL8QOYdDPabbURpXv2EW7dhBqO5xM7DsttK1IaJnmXJLZ6LHWr7gL84ViflAbWG8M4tI5bQgjTpPZSJjwNqoTj3ADJb7vVpjpEep7KEndkyxwq2g8bnbx5iy01fQba/q/W2tTjnGD7ZUpLTMocbIkOXIQsCRr8PCKjOY/FJQlbiIQrZRw7Wk+H6OrDg6cRi2Gnw2tDjkLHKY7QgkXSim7l3COGK7IiP8UugueiaPpk78zeXDNljqTQDiJYUfRt2f+v1KpPeqPxFi3UYh1DfLCQtwgclgxCiE3KJPtpxvFOET6O8H5hjfx7m96PmRTwY2t0h5niZwLSUtNA850fpPX06jde9Wj3HOJVyZbZ9Iz2rLsEuONiO34IHvJrLpzF/mEeigLt6DD7xJj0e8gV6fmGTsJexiUtt6cPgkLaHLb7K1aiVTpneTG0qNqFYS4fH3SMMvi11ZbUppoK5bsEcy1lbduPjStcY4jTBS2oKaWTIVO6k7pUOgFVOFx75XB5cSsD0LG37n7VSMfinUhOkN3On5ljYySO57aVMrkw8ItEcbYhKYDbMJbSoWXvIH0/OmXOMcT2lQ3s2uIVplSRI70x2j1nzqz/B4wMViEIfS2tJQ5I5TInSDAJSgGAQPhWcRjXCtSSW41BI9CxcDp3OkUaWS8OPwie9xY+FOEIaB5qbwu86uhXA26Up4qfKxCW0+nUDpCrgkTOpR+yKjZliXUtlSdEyP0TNzP7G9zVnwWFYjFMtu6VJU6dQDbQkCTuEyJgedOmPlQ8Ir/yrfVpWUNSW3Umy4gJUfp+ZpFcVvp1EJaEst9F9HBHr+dJnuKcRjHm29KW0PFKQENQEkkaRKZ8KPGvupbUQRIFuw3429WjS2HLxpVS3GDn7oWtUIJU6yYVzCAdKjKRrgUaeJX9aY0CFvCAFQQEjftVO4PLj2JabdVKVPNhQhAlNpFhbqPGmOLXnW8a+20opQlRhKYgdqIE+VGlj0Q6UiuRnzo7Q0CGdu3p+d+iVR796dxPFGLCjpDcW9XqQCevnW0w3CGOLaluOcoBCjBVKrCe6m3xNVP4kxRSkl9wEpBI1xE3i58IpuLRnNYm/mivyetZxiC3h3nEgEoacUAdiUoJAPlaqDI8yxD7GJLyWQPkxUnlhwHtIV3tdo7Noq14gxbSsJiEoWhRLDwASpJJJbMAAG5qh4VxaThHpsRhiiJudIUBbxhQpt7jbakkU/wCEXGLaxOEcQltWlvV29UWVI7t4r0lknlgmJ0AmJiYvHlXln4QyHHsKlJCpZKeyQblURA6ma2mU52l99TZSkctrsmZN7Ee23TaDe9Jy6WNdWir4ezJ3ENOuLZw6UnCPKBbSvXM6YlQjTY9dwKjcZZLhm1YdDWHb1O8wSpTgA0pSbBJI6np0p7hRhTbC0KSoH5E7YiCDqJv+9UzjcgYjB6t0c5REiwUEISTOwMm/kaWvaxKTcbM1w3w0MQtxsrCChKVDSNSe10vBiD5UfAeUvDGhzS1y0KxCdQ1BR0oKZgiN1DrV/wAFFCMTiAVpslCZ1D1bfypvgjALbdWpS1nW5i1BJkABRSRaduxb9qtG2K1sYvP8jxLLbThaSoOqSE3BnUkqFheY8qDL8C6GUuLaKUnVBCVaN4sSK2ucuDFYfBpbWUlrEMhUXI5bRJJg2SZifOpObgN5WGyuSkpFyJPpJkiaSlbG3vR55wwo/jBgctAHyhI1etZe+29bTO0D5NjvPHf9oprhHCJbcbedi+IOiUie2AhJCidpvYeNa7GMMcn0iEFBxDhcEDtEFcao3Nh8BRY4y1I8Py1taiVctCUpG4N7mBaKn40RhwQAe0ux2NkVqOC+GWnClT4UUqCzElKbqOgAhVyNJ9oIPWqvNsCg4R4IMKGJLSL9FD/0pvQmmrC2T/wdIUpLwCAFHBPAAdSXSB/KrnKeDWGCHHRzXBtqAhP7Kdp9sml4KyxOHbKtSiuC0ZA09/UoggmbiPftWj5hO9RLcq9tiuxSkqJSpIKTYggEHyIO9VeT8JMM4tnEtS0lDkrTugyCm03QZUPEeQq0B7RPmaZfeKk6CRBnfbY7+VSluCkeScSuxinoQFDmOXJ+uanYRCCE9LCRP8D0qlzdoqdcPT0hteRM2G9eg4XgR7lB0PslOjWIKjI0zYxXR9xPpsY5WGHOUkITZcaiQDtJMe4ivVs1QedmX/6jY+w151keSuv4hMLSAt0gdSOySdQH93r13HYXSca8pQCVIQi4nSUgEqPiO0LeVRqQJ2eGYVPpI5Wm6ryOnWPOpWZwEplGrtARIHTe9afI8iWpTj7jiS0PlKk6QLpBGkxNu6enWomcZclxOGCSkFboKjbu61pMDr3ftqk9iFPuyX+CtIOJQQkJBbesCD0PUeNY9laC9pDdwsjVI3iZjevXuAuHGGGkrBUp1LawSbC5UmyR0t/c1T8PcIsDElxSUkc9yEkk2CSkAg+BvRqLtd/wYnMgA3JTruLDf21dfg5wq1YllaWlJSlxUmJA7JuVCwq4xmCTGLQEpAViGkpGwA5xsCB2RArbcKJSGoSIAdd2EDvk2+IpuW9GcX81HjnEGCUcW+SwSA6FFepIEKMoME3t0F7VpU8LI+RnFPOEo0ayhAAVExGtRgVI4gT2cSZ9dq0H9THT9oVaPp52SrbQRqLPLv0Vri/lRrdCg1J79juA8lwcl1vDLQpC0wpxwKJMTqASrSN/Cay2buspzZQcYU9I7qSLmTpJBUAYN63nCOAU0nSo352rslQEcsCDtN0ny2rzzPcKpWb6hGkFA6kyFhZgAE7Cp1Ua/LV9j1vGEBpZUJAQskDcjSZA91YNHEOGgacDiyCBEKERED9N5R7q3Oag8h3/APE5/wDwa8kb4df5bQ7MpaQk2WbxO4SR1obJlo/mokP54wtJ04INq0qAKCAL9YAvt9p8TTOW47DtsqbcwqllSiSqRMGAQFCCBWsR+D/DJ2fP77f3UTvBbIEF4kEgfONjf2AV5P8AGu9joUZdykRmmXkpUrBdpPdkAlPW0qteouHxODDylqbeWlU9lQTYyCL6pPx8K1COBsKPXJ9rw/qqSjgvCeE/6v3Kqv419g0yM0vM8EpKkIbeZ1Ap1IJCotqAMqsbfCoOatYBQRyRy9O8NbiZgQB1JPvq6xfD6wr83wrak+qtT7igQdiEpMD41Z4LKAEjm4NGuLkBRBPWATYUS5zVOvcpJoqMnfy1EgM6lrN1KbhRlUielpj3Va5pmWEbcWhKgy8AkhwJM9tPQgeEg1Ow2X6YIwqExtCFfxIosVhXVK1BlAsBJSCbeZFaKWTTuS1uZbhbDYeXAshRCdUpQo2T5qRufb12q4cy/BONhbjQU2pRCQULBlMd4Jibz0AgD32uDwzwPagD2I/lUTEJxKkBBcbABJnVBM+Ok04txiHcqs7wCVIbThGkjSpPQiAkjTZW4Ef+9VnE2YOoRyA4FLKg45pSEqUskkkjyTAtAtTue475Lp14ka1mEhMnynb2fGse2+ouKS4oHujU4rtGB5mVHsiscmSST/ew6RpcpGEDbYefSlV5BLU3SfWJPWAKlDLMFyj8nw7qlalLQsLSQlyBpc7KgFRG19qyzyFqbWEKiUwkJbUnVPRCvWVF4HQVu8mC+Wkr1G0yuAoDoFefnFXinKUb6F48bkrexN4XwLgaCCVLUStWxESokmOm9/bVxhnGQ4WyrUtJhYAUUtnolRAura3gQbip+SPkMLWIVp5nZTFylKSAYG9z8ay+FzV0X5aQSL9hkb3giBN/KupypWTHDbaLPH4ZsAFKwkTHakJmYAC1Re4sfjVNikqSk3KVDrFwYkGPHrULiDMlyhSm2DcAakINtQ2BTHWizHHOjAsrBTpViFtEBCYCQ3qGkju3MHxojOwlw7TMrj+HHFo1Na1zIJS2SSSDckJ8al8C5XhlNuNYgQUG4JiAoHUL3TcG1rzW74dxq2G2FhJ0lN4jYnqNyfrXFeZqxh+V4wpI7Tjyk3iU80mDNiYWD/lNVxGrHj1DxpTbiuxNWEMobSkITpUSsIKyDJ3vMEpCRawj3Vqn14nEsullTbq3gNXLEnQEiERsCL7XvWVeecKZaOhWk9oGDcaY9lyKe4VzEpbdbaKQsAqbCRBChZRJ8Yj7a8vBncrb7+xLp0aNrhdzk8t5JAIhWhDurTeyTJF5v2eloosHwdgWVJVy3SUA6SpblpkmwI+kd/GqZnjfGxd0g3BHUEWO9SWeO8Z+s+MVv8WlsPQTsK0trEuOIcS3KdKSo6pBEEQTJjzqRhOVKgp4BOmANRiNMEiVfxFPZdxZjFjvJNvpI/maX8tnkmHSjr1STtawM71SyqkyXFMx2dYXCNwvDPpIKgpcvdqdU6gZ+wda0mDzPA2caeWlYuCpbigk+YUSk77UX/xFWd2kH2gVIb40Sq6sOx7wPurNcQrbTLcDMjDpdDofxroGtWlKVgIWNwVJAMAnpanOH1sNag68SARpDZdIjxVAg+N60KOJmHDHyFlY8m0n7IqSc0w4Hby9CfAcoC3wpxySdNPp9yHFLYg4vG4dIaWH3U3XpUFL8LkiL3I38axanEjEFxTrveUeYAoruLKACDFvG1egLznLyEhWFbMlUJi4gCTHn/Ko/wCNMrJg4QD2Ej+dGXJOTW69xwjRUpzHCkXxGK85U5f2ilGZYMeu79v3Va87Ko/6Uj2KV99R1/iWb4dQ/wBRX9VHPzeV7icEODO81G62Pemkcz/MTF8KYIP93qkSSOgE+I++lDxB7w+Kf5mq5T8v1NbXgvvyixxEKThLjz/qo/ylxXVGE+J++qBOIVuCI8dSPvpFY2DBWkE3jUnb3U+U/LFa8GhHE74/RYT4qo08UO/qcL+8v+mssvMmpgvtg/t/d7qFeZMxJxDUftn7qfKl5YjWjiRZ3w7B9inf6KX8cpO+EY/ec/8AKrHqzZkb4lr95R/gKVWbs/8A3LR/zK+6qWCT7v8Af4HTfY14zlA2wjPuW5/5VR8Tm+GbSVu4JpKRudThgRJJhqwrKfj3DgT8obj/AFJ+GiaYfzrCqKHFYhMoVKRDl+kKRo7Q8iKpcM+jv0/wGmXgp+Jc0wr2LOJbUCISENGQlsJSAbqAJkyRaO0fCoL2d9skpSnUACU9o3SD1IHvqW5hcrJJ5pkkn9MBJMmAEW+FWWXYTCFnE8tY5YbSXiSuNKT6MAlM6jHq33q5cPBr5osl431ZdZA008wlSit1Jk6VpTAUDEwBc28fCr74/A/dWFyheDaALZCTcj0qlC/XeL77dathnKCDDqPgo/ZF6yqMdk1RpzGuhscyxSUZW8SYJWQkAwpUFtSgmxlWlKjHWKp1pQnvuKv1UqAfhA6ioi86QvDpYKU9lwulZVp3TGxjT7Zp5XDmLDrbqWgdKXNlt31ARJKu1sa1lDZIIZqtldniUjQpDaiEqGpUR4dVAE7zadql5vqOXMFII/PVyJ2HJAuSmwkjfxpvG5fmj7ehTaJBIUokX0yiwSIHx6VGzXEA4JOGWIUHVOFadRBCkaYCUidgOvSnGISy2U76ca4txDK45OkREkSnUADouJn41Qv4N0PBSyEHUDJ1wTNzISPHar3DOoSOxiEXiSnSJPQnUsE/bXO5W46qUYhMqMgBQMmPAH7963y8VzYxjKqSrb9SFKnaRaryV4CQUqSYggKAIO0Az4+NDhMkxKHAprQkSNQuTEQoA9JE/GpOEcewrYYfxCNQ0qSOYe4SCk9qOoPwqQnOge0H0Te+u9t9+n8jXnfCxTtC0Sl0TL53KsASVHBO9o6j29/PvRUdWW4AbYBw/wCqP66hIzt1Q0pfSoRsHERG3jtQpxTx3Kf307dN4iqlgXf9Cqn9SwThMENsvX/uo/rruVhAbZer/eR/VUMvvDeBf6SN/DekGIdnx96f5VPI+vsv7E2ySG8H/wCGn/dSf+6uUMKP/pp/3P8Am9NfKXvofZ/zSHGOfqz+6qjkfX2X9gth6sONsrP75+6nFZg3Efi5Uea1R/CuwGPAWC+24UAEkJCpJiwt41Y4bMMudCVrXisI5JlCEvEeXpOURBHQEbmaqOB/1V6AU6cY0NssHxP9NCvMmhf8WI+3+ir53FZWBodxr7iJCuYtaRylGQBJSlRmI0kKG1hvWawvEDLilpbUohKlCUpc0ESdJSpcTaKUuHa6S9kMeVnDfTLU+8K/opv8aj/w5v8AdV/TUkZknqo79dW320284woySg+0X+0Vm8U/6n6Iex5gjFfWWnwkqtbpexpXUDYyY+sr2zc703qK1p1eIHuo0HVfafD2UNtdGe/ws1kbUoRa+yES0naFfvGhVhUef2H+VGP7+FSFK0oWoASAInzsTS5k13OjNhwRi5OK/BEVhE7An4D7675IB18On8b1YYa6O1eQFXvfb+dOLZSCBAvqPw9laRnkfcww8Phy7qHuytTg0k3UR5JFh8TtRowKPFR8bj3RY1YhlMTe/mY3H30ASN4G/n5/cK0jkzLpKjrx8Nii60L1ZCXh2k77+BJ/gKlYVhJuluAYuQNp3Gr+VOMxvA69PA/80LmJUCY9lOUZS3lJs6uXixLmSSS+i39yLmKU6ghQCABOq5J8AAkWmevhvVjxG38mwbGCTJdfh94ReDZpuPGwt4pPjQcLtB/FsJd7QKxI8YlUey1SMGs4jOlqdvpfdA8IakNj3aAfbWmN1HT2R8rxWXnZW/L/AH0NZlXCzLTLaFtoUpKRqJSJKvW+2p4yPDbclv8AdFWakU4lgeJrDSmzSopdCnRkGFSQQ0mQQep+wmDVyMW4PWP2Uy4iK5N6ptj0RfYsUZn9Q/H/AIrOKyDCndsGSSSSdyZOxq1CaRIo1MlYoLsU54Ywg2ZbA8ACKB7h7CpBJZRAE9fvAq7NcpNr38jWbihuMfBgvwi4dKmsJiG+7pLJ910D3aV/GsOWzOlNyTAjevWuN8Kj8XOwI0qQoR0JWAf4n4mvL8pdKHRp6yCYBMeU7V6XDZHHHa7CwNatL8hYbC6FHnIPdtYlN+pKdvb5VJeZZXZBSCJug2PjY/yq3cWdtxE3ph1tJmUptfbrY7+/7K58zlOWvU0/b0Pqof8AHxxqtpJ+Vv6oqVYFRHzhgXgzv8be2hGFeT3XDHkpQq4fw4CZlWyjuenS/S5oPkwCT2lG25jw9nlWbz54d0/wc2bg+Gh/2g19nf8A6VjhxKt1unxl0kHqDvXYdOKSIS4pIjos/wAqdQ8T9v8ADz9lAl1UEz4eHUgfzp87PVbHDKPA9al7Dzb2JFy+ozuAtYO0C8VOZzbFiAcSr2BCDb2nyqDgMSo9k6SCD6qZFuhiRTriBJ8hP21yz4nIpU69EPDDgZ/yP8ssU57ibS8oxv2EX+6jTn+IJkqQR4aL/HVVETvRhRCTHh/C9Q82R9/ZHVPhOEUHPR0Lj8evjfln/TAnxmT/AAjanVcRA+o0P8oP/cKyWIJJuSd9zP8AGpuByxtaAozJ9n3VtJOMblL0R5MXhySqGJerP//Z" ,
            "Shymkent medical institutions are among the best in Kazakhstan" ,
            "https://otyrar.kz/2025/04/shymkentskie-meduchrezhdeniya-voshli-v-chislo-luchshih-v-kazahstane/")
        Spacer(modifier = Modifier.padding(4.dp))
        newsitem("https://azertag.az/files/galleryphoto/2025/1/1200x630/1744186064571885262_1200x630.jpg" ,
            "A business meeting and a visit to a number of leading medical centers in Kazakhstan took place within the framework of the V meeting of the Ministers of Health of the OSCE member countries." ,
            "https://azertag.az/ru/xeber/v_ramkah_v_zasedaniya_ministrov_zdravoohraneniya_stran_chlenov_otg_sostoyalas_biznes_vstrecha_i_poseshchenie_ryada_vedushchih_medicinskih_centrov_kazahstana-3495083")
        Spacer(modifier = Modifier.padding(4.dp))

        newsitem("https://dknews.kz/storage/news/2025-03/1d137d27a86efc6da80befe906ba406f-1280px.webp" , "Children's Hospital No. 2 in Almaty has become the best in the country" , "https://dknews.kz/ru/v-strane/356031-detskaya-bolnica-2-v-almaty-stala-luchshey-v-strane")
        Spacer(modifier = Modifier.padding(4.dp))
        newsitem("https://cdn-kz.kursiv.media/wp-content/uploads/2024/10/1395323251076.jpg" , "Six international development institutions will allocate 365 million euros for the construction of a hospital in Kokshetau" , "https://kz.kursiv.media/2024-10-31/lgtn-hospital-kokshetau/")
        Spacer(modifier = Modifier.padding(4.dp))
        newsitem("https://img.inform.kz/kazinform-photobank/media/2025-01-03/89c47849-d772-43cd-b978-b8d170990654.webp" , "The One-Day Clinic project is being scaled to the whole of Kazakhstan" , "https://www.inform.kz/ru/proekt-klinika-odnogo-dnya-masshtabiruyut-na-ves-kazahstan-34dcf0")

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
fun newsitem(photo :String , text: String  , modelurl:String){
    val context = LocalContext.current
    Card(  modifier = Modifier
        .fillMaxWidth().wrapContentHeight()
        .padding(8.dp).clickable {
            val intent = Intent(Intent.ACTION_VIEW,Uri.parse(modelurl))
            context.startActivity(intent)
        },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)){

        Row{
            AsyncImage(
                model = photo ,
                "" ,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(80.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)  ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text= text , fontSize = 16.sp , fontWeight = FontWeight.W300)

            }

        }




    }
}