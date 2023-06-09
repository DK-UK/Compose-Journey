package com.example.myapplication

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.loader.ResourcesProvider
import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.DurationBasedAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface {
                    myApp()
                }
            }
        }
    }

    @Composable
    fun greeting(name: String) {

        Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Row(modifier = Modifier.padding(10.dp)) {
                var isExpanded by rememberSaveable {
                    mutableStateOf(false)
                }


                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(text = "Hello,")
                    Text(text = "$name")
                }

                IconButton(onClick = { /*TODO*/ },
                    ) {
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun previewGreeting() {
        val list = mutableListOf("dhaval", "dk")
        Column {
            list.forEach {
                greeting(name = it)
            }
        }
    }

    @Composable
    fun myApp(){
        var showOnBoardingScreen by rememberSaveable {
            mutableStateOf(true)
        }

        if (showOnBoardingScreen){
            showOnBoardingScreen(onClicked = {showOnBoardingScreen = false})
        }
        else{
            showGreetings()
        }
    }

    @Composable
    fun showOnBoardingScreen(onClicked : () -> Unit) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to onBoarding screen",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Button(onClick =  onClicked) {
                Text(text = "Get Started")
            }
        }
    }

    @Composable
    fun showGreetings(){
        val list = List(1000){ "${it}"}
        LazyColumn {
                items(list) {
                    greeting(name = it)
                }
        }
    }

    @Preview(name = "On Boarding Screen", showBackground = true)
    @Composable
    fun previewOnBoardingScreen() {
        myApp()
    }

    /* @Composable
     fun previousCard(msg: Message) {
         Row(modifier = Modifier.padding(8.dp)) {
             Image(
                 painter = painterResource(id = R.drawable.ic_launcher_background),
                 contentDescription = "background image",
                 modifier = Modifier
                     .size(40.dp)
                     .clip(CircleShape)
                     .border(1.dp, color = MaterialTheme.colorScheme.primary, shape = CircleShape)
             )
             Spacer(modifier = Modifier.width(10.dp))

             var isExpanded by remember { mutableStateOf(false) }
             val surfaceColor by animateColorAsState(if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
             Column (Modifier.clickable {
                 isExpanded = !isExpanded
             })
             {
                 Text(
                     text = "author - ${msg.author}",
                     color = MaterialTheme.colorScheme.secondary,
                     style = MaterialTheme.typography.titleSmall
                 )
                 Spacer(modifier = Modifier.height(4.dp))

                 Surface(shape = MaterialTheme.shapes.large, shadowElevation = 5.dp,
                     color = surfaceColor,
                     modifier = Modifier.animateContentSize().padding(1.dp)) {

                     Text(
                         text = "body - ${msg.body}",
                         *//*color = MaterialTheme.colorScheme.secondary,*//*
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }

    @Composable
    fun showMessageList(message : List<Message>){
        LazyColumn{
            items(message){
                previousCard(msg = it)
            }
        }
    }

//    @Preview
    @Composable
    fun previewMessages(){
        showMessageList(message = messages)
    }

    data class Message(
        val author: String,
        val body: String
    )

    private val messages : List<Message> = mutableListOf(
        Message("dk", "hello there!" +
                "dslfkjsflksdfljflsjflksdj"),
        Message("dk", "hello how are you doing!\n this is  slfjsdl ffhgh"),
        Message("dk", "what's up dude!\n dllsdfjdjlflkfl"),
        Message("dk", "hows your compose journey going on ?!"),
        Message("dk", "Fantastic!"),
        Message("dk", "ohh that's great!"),
        Message("dk", "yes it is!"),
        Message("dk", "And every other thing ?!"),
        Message("dk", "yeah going well though"),
        Message("dk", "c'mon brother!!")
    )*/
}
