package com.example.todo.navigation

enum class NavigationScreen{
    HomeScreen,
    DetailScreen;
    companion object{
        fun fromRoute(route:String?):NavigationScreen
        = when(route?.substringBefore("/")){
            HomeScreen.name -> HomeScreen
            DetailScreen.name -> DetailScreen
            null->HomeScreen
            else->throw IllegalArgumentException(
                "Route $route is not recognised"
            )
        }
    }
}
