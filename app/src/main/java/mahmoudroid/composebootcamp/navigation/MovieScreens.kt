package mahmoudroid.composebootcamp.navigation

enum class MovieScreens {

    HomeScreen,
    DetailScreen;

    companion object {

        fun fromRoute(route: String?): MovieScreens = when(route){
            HomeScreen.name -> HomeScreen
            DetailScreen.name -> DetailScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not specified")
        }

    }

}