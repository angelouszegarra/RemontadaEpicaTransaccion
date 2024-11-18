package com.example.remontadaepicatransaccion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.remontadaepicatransaccion.ui.theme.RemontadaEpicaTransaccionTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Verifica si el usuario está autenticado
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            // El usuario está autenticado
            println("Usuario autenticado: ${currentUser.email}")
        } else {
            // El usuario no está autenticado
            println("Usuario no autenticado")
        }

        setContent {
            RemontadaEpicaTransaccionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RemontadaEpicaTransaccionTheme {
        Greeting("Android")
    }
}

