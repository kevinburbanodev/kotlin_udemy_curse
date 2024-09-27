package org.example.expensesapp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import moe.tlaster.precompose.PreComposeApp

@Composable
@Preview
fun App() {
    PreComposeApp() {
        val colors = getColorsTheme()
        AppTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "") }
                    )
                }
            ) { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                    Text(
                        text = "Bienvenido",
                        color = colors.textColor
                    )
                    Text(
                        text = "Curso de kotlin multiplataforma con compose"
                    )
                }
            }
        }

    }
}