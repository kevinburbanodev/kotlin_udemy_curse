package com.example.introduction

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.introduction.ui.theme.IntroductionTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var nameText by remember { mutableStateOf("Hola Mundo") }
            var isClicked by remember { mutableStateOf(false) }
            IntroductionTheme(darkTheme = false) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "")
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Cyan // Color azul para la barra superior
                            )
                        )
                    },
                ) { innerPadding ->
                    Card(
                        modifier = Modifier
                            .padding(innerPadding)
                            .clickable {
                                nameText = "Clicado"
                                isClicked = true
                                Log.d("State", "Cambio de estado")
                            },
                        shape = RoundedCornerShape(20),
                        elevation = CardDefaults.cardElevation(15.dp),
                        border = BorderStroke(1.dp, Color.Red)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = if (isClicked) painterResource(id = R.drawable.baseline_mouse_24) else painterResource(
                                    id = R.drawable.baseline_person_24
                                ),
                                contentDescription = "Profile"
                            )
                            CustomText(
                                Modifier
                                    .wrapContentSize()
                                    .background(Color.Gray),
                                nameText, 20.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomText(modifier: Modifier = Modifier, text: String, fontSize: TextUnit): Unit {
    Text(
        modifier = modifier,
        text = text,
        color = Color.Green,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview
@Composable
private fun CustomTextPreview(): Unit {
    Row(
        Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_person_24),
            contentDescription = "Profile"
        )
        CustomText(
            Modifier
                .wrapContentSize()
                .background(Color.Gray)
                .clickable {
                    Log.d("Click", "Hiciste click")
                }, "Hola Mundo", 20.sp
        )
    }
}