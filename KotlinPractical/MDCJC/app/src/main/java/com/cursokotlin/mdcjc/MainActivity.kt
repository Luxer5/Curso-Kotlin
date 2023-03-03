package com.cursokotlin.mdcjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cursokotlin.mdcjc.ui.theme.MDCJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MDCJCTheme {
                ContentMain()
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContentMain() {
    Scaffold(modifier = Modifier.fillMaxSize(), 
        backgroundColor = MaterialTheme.colors.background, 
        bottomBar = {
        BottomAppBar(cutoutShape = CircleShape) {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Menu, contentDescription = "Menu")
                }
            }
            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "Options")
            }
        }
    },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Create, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true) { padding ->
        Content(Modifier.padding(padding))
    }
}