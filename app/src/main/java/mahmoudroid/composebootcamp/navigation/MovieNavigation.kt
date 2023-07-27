package mahmoudroid.composebootcamp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import mahmoudroid.composebootcamp.screens.DetailScreen
import mahmoudroid.composebootcamp.screens.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name){

        composable(MovieScreens.HomeScreen.name){
            // show home screen
            HomeScreen(navController)
        }
        
        composable(
            MovieScreens.DetailScreen.name + "/{movie}",
            arguments = listOf(
              navArgument(name = "movie"){
                  type = NavType.StringType
              }
            )
        ){
            backStackEntry ->
            // show detail screen
            DetailScreen(navController = navController, backStackEntry.arguments?.getString("movie"))
        }

    }
}