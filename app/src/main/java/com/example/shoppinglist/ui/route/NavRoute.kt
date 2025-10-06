package com.example.shoppinglist.ui.route

sealed class NavRoute(val route: String) {
    object Home : NavRoute("home")
    object Profile : NavRoute("profile")
    object Settings : NavRoute("settings")
}