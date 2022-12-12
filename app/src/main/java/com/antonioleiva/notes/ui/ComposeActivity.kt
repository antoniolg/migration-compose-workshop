package com.antonioleiva.notes.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.antonioleiva.notes.App
import com.antonioleiva.notes.data.NotesRepository
import com.antonioleiva.notes.ui.detail.DetailScreen
import com.antonioleiva.notes.ui.detail.DetailViewModel
import com.antonioleiva.notes.ui.main.MainScreen
import com.antonioleiva.notes.ui.main.MainViewModel
import com.antonioleiva.notes.ui.ui.theme.MigrationComposeWorkshopTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MigrationComposeWorkshopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val app = LocalContext.current.applicationContext as App
    val repository = NotesRepository(app.notesDb.notesDao())

    NavHost(navController, startDestination = "home") {

        composable("home") {
            val mainViewModel = viewModel { MainViewModel(repository) }
            MainScreen(
                vm = mainViewModel,
                onAdd = { navController.navigate("detail/-1") },
                onClick = { navController.navigate("detail/${it.id}") }
            )
        }

        composable(
            route = "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: -1
            val detailViewModel = viewModel { DetailViewModel(repository, id) }
            DetailScreen(
                vm = detailViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }

}