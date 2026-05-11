package com.example.daapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.daapp.ui.theme.MainScreen
import com.example.daapp.ui.theme.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "splash") {
                    composable(route = "splash") {
                        SplashScreen{
                            navController.navigate(route = "main"){
                                popUpTo (route = "splash") {inclusive = true}
                            }
                        }
                    }
                    composable(route = "main") {
                        MainScreen()
                    }
                }
            }
        }
    }
}

