package com.example.e_commerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e_commerce.presentation.login.LoginScreen
import com.example.e_commerce.presentation.login.LoginViewModel
import com.example.e_commerce.ui.theme.ECommerceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ECommerceTheme {
                val loginViewModel: LoginViewModel = viewModel()
                LoginScreen(viewModel = loginViewModel)
            }
        }
    }
}
