package com.example.firebase.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.firebase.ui.view.DetailScreen
import com.example.firebase.ui.view.HomeScreen
import com.example.firebase.ui.view.InsertMhsView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
    ){
        composable(DestinasiHome.route){
            HomeScreen(
                onDetailClick = { nim ->
                    navController.navigate("${DestinasiDetail.route}/$nim")
                    println("PengelolaHalaman: nim $nim")
                },
                navigateToItemEntry = {
                    navController.navigate(DestinasiInsert.route)
                }
            )
        }
        composable(DestinasiInsert.route){
            InsertMhsView(
                onBack = {
                    navController.popBackStack()},
                onNavigate = {navController.navigate(DestinasiHome)
                }
            )
        }
        composable(
            "${DestinasiDetail.route}/{nim}",
            arguments = listOf(
                navArgument(DestinasiDetail.NIM){
                    type = NavType.StringType
                }
            )
        ){
            val nim = it .arguments?.getString(DestinasiDetail.NIM)
            nim?.let { nim ->
                DetailScreen(
                    onBack = {
                        navController.navigate(DestinasiHome.route)
                    }
                )
            }
        }
    }
}