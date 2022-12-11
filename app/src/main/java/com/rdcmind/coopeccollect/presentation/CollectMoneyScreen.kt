package com.rdcmind.coopeccollect.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rdcmind.coopeccollect.presentation.selectmembre.SelectMembreScreen

@Composable
fun CollectMoneyScreen(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "select_membre") {
        composable("select_membre") { SelectMembreScreen(navController) }
    }

}