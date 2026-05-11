package com.example.daapp

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.daapp.model.DoctorModel
import com.example.daapp.ui.theme.DetailScreen
import com.example.daapp.ui.theme.MainScreen
import com.example.daapp.ui.theme.SplashScreen
import com.example.daapp.viewModel.MainViewmodel
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    private val viewmodel = MainViewmodel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "splash") {
                    composable(route = "splash") {
                        SplashScreen {
                            navController.navigate(route = "main") {
                                popUpTo(route = "splash") { inclusive = true }
                            }
                        }
                    }
                    composable(route = "main") {
                        MainScreen(
                            viewmodel = viewmodel,
                            onDoctorClick = { doctor ->
                                val doctorJson = Uri.encode(Gson().toJson(doctor))
                                navController.navigate(route = "detail/$doctorJson")
                            }
                        )
                    }
                    composable(route = "detail/{doctorJson}") { backStackEntry ->
                        val doctorJson = backStackEntry.arguments?.getString("doctorJson")
                        val doctor =
                            Gson().fromJson(Uri.decode(doctorJson), DoctorModel::class.java)
                        DetailScreen(
                            item = doctor,
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}

