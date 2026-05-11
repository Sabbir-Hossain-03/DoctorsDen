package com.example.daapp

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.daapp.model.DoctorModel
import com.example.daapp.ui.theme.DetailScreen
import com.example.daapp.ui.theme.LoginScreen
import com.example.daapp.ui.theme.MainScreen
import com.example.daapp.ui.theme.SignUpScreen
import com.example.daapp.ui.theme.SplashScreen
import com.example.daapp.viewModel.MainViewmodel
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    private val viewmodel = MainViewmodel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                val auth = FirebaseAuth.getInstance()

                NavHost(navController = navController, startDestination = "splash") {
                    composable(route = "splash") {
                        SplashScreen {
                            if (auth.currentUser != null) {
                                navController.navigate(route = "main") {
                                    popUpTo(route = "splash") { inclusive = true }
                                }
                            } else {
                                navController.navigate(route = "login") {
                                    popUpTo(route = "splash") { inclusive = true }
                                }
                            }
                        }
                    }
                    composable(route = "login") {
                        LoginScreen(
                            onLoginSuccess = {
                                navController.navigate(route = "main") {
                                    popUpTo(route = "login") { inclusive = true }
                                }
                            },
                            onSignUpClick = {
                                navController.navigate(route = "signup")
                            }
                        )
                    }
                    composable(route = "signup") {
                        SignUpScreen(
                            onSignUpSuccess = {
                                navController.navigate(route = "main") {
                                    popUpTo(route = "signup") { inclusive = true }
                                }
                            },
                            onLoginClick = {
                                navController.navigate(route = "login") {
                                    popUpTo(route = "signup") { inclusive = true }
                                }
                            }
                        )
                    }
                    composable(route = "main") {
                        MainScreen(
                            viewmodel = viewmodel,
                            onDoctorClick = { doctor ->
                                val doctorJson = Uri.encode(Gson().toJson(doctor))
                                navController.navigate(route = "detail/$doctorJson")
                            },
                            onLogoutClick = {
                                auth.signOut()
                                navController.navigate(route = "login") {
                                    popUpTo(route = "main") { inclusive = true }
                                }
                                Toast.makeText(this@MainActivity, "Logged Out", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                    composable(route = "detail/{doctorJson}") { backStackEntry ->
                        val doctorJson = backStackEntry.arguments?.getString("doctorJson")
                        val doctor =
                            Gson().fromJson(Uri.decode(doctorJson), DoctorModel::class.java)
                        DetailScreen(
                            item = doctor,
                            onBackClick = { navController.popBackStack() },
                            onMakeAppointmentClick = {
                                viewmodel.makeAppointment(doctor) { success ->
                                    if (success) {
                                        Toast.makeText(this@MainActivity, "Appointment confirmed with ${doctor.Name}", Toast.LENGTH_LONG).show()
                                    } else {
                                        Toast.makeText(this@MainActivity, "Failed to make appointment", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
