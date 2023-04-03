package com.example.ProfileChatCompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ProfileChatCompose.ui.theme.MyTheme
import com.example.ProfileChatCompose.ui.theme.lightGreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MainScreen()
                  }

                }
            }
        }
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(userProfiles: List<UserProfile> = userProfileList) {
    Scaffold(topBar = { AppBar() }) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
        ) { LazyColumn {
            items(userProfiles) { userProfile ->
                Profile(userProfile)
            }
          }
        }
    }
}
@Composable
fun AppBar() {
    TopAppBar(navigationIcon = { Icon(painter = painterResource
        (id = R.drawable.baseline_home_24,),
        modifier = Modifier.padding(horizontal = 13.dp)
        , contentDescription = "sdfs")
                               },
        title = { Text(text = "Mesaaage")})
    }
@Composable
fun Profile(useprofiles : UserProfile) {
        Column(modifier = Modifier
            .wrapContentSize(align = Alignment.TopCenter)

        ) {
               ProfileCard(userProfile = useprofiles)
        }
}
@Composable
fun ProfileCard(userProfile : UserProfile) {
    Card(
        elevation = 8.dp,
        shape = CircleShape,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfilePicture(userProfile.pictureUrl, userProfile.status)
            ProfileContent(userProfile.name, userProfile.status)


        }
    }
}
@Composable
fun ProfilePicture(pictureUrl : String, status : Boolean) {
    Card(shape = CircleShape,
    border = BorderStroke(width = 3.dp,
        color = if(status == true )MaterialTheme.colors.lightGreen
        else Color.Red),
        elevation = 4.dp,
        modifier = Modifier.wrapContentSize()
        ) {
//        val asyncImage = AsyncImage
//        Image(modifier = Modifier.size(72.dp),
//            painter = painterResource(id = drawableId),
//        contentDescription = "nema veza",
//        contentScale = ContentScale.Crop)
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pictureUrl)
                .build(),
             modifier = Modifier.size(100.dp),
            contentDescription = "ImageRequest example",
        )
    }
}
@Composable
fun ProfileContent(userName : String, status : Boolean) {
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
    ) {
        CompositionLocalProvider(LocalContentAlpha provides  if(status) 1f else ContentAlpha.medium) {
            Text(text = userName, style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 10.dp))
        }


        CompositionLocalProvider(LocalContentAlpha provides  ContentAlpha.medium) {
            Text(text = if (status) "Active now" else "Offline",
                style = MaterialTheme.typography.body2
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTheme {
        MainScreen()
    }
}