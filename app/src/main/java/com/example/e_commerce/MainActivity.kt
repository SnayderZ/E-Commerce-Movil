package com.example.e_commerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.e_commerce.presentation.login.LoginScreen
import com.example.e_commerce.presentation.main.MainScreen
import com.example.e_commerce.presentation.login.LoginViewModel
import com.example.e_commerce.ui.theme.ECommerceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ECommerceTheme {
                // Crea un NavController
                val navController = rememberNavController()

                // Configura las pantallas de la navegación
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        val loginViewModel: LoginViewModel = viewModel()
                        LoginScreen(navController = navController, viewModel = loginViewModel) // Pasa el navController a LoginScreen
                    }
                    composable("main") {
                        MainScreen() // Pantalla principal después de login exitoso
                    }
                }
            }
        }
    }
}