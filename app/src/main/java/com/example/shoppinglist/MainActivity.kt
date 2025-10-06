package com.example.shoppinglist


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import com.example.shoppinglist.ui.screen.*
import com.example.shoppinglist.ui.route.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShoppingListApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListApp() {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menu", modifier = Modifier.padding(16.dp))
                NavigationDrawerItem(
                    label = { Text("Setting") },
                    selected = currentRoute == NavRoute.Settings.route,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(NavRoute.Settings.route) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Shopping List App") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },

            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = currentRoute == NavRoute.Home.route,
                        onClick = {
                            navController.navigate(NavRoute.Home.route) {
                                launchSingleTop = true
                                popUpTo(NavRoute.Home.route)
                            }
                        },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") }
                    )
                    NavigationBarItem(
                        selected = currentRoute == NavRoute.Profile.route,
                        onClick = {
                            navController.navigate(NavRoute.Profile.route) {
                                launchSingleTop = true
                            }
                        },
                        icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                        label = { Text("Profile") }
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = NavRoute.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(
                    route = NavRoute.Home.route,
                    enterTransition = {
                        slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300)) + fadeIn()
                    },
                    exitTransition = {
                        slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) + fadeOut()
                    }
                ) {
                    MainScreen()
                }

                composable(
                    route = NavRoute.Profile.route,
                    enterTransition = {
                        slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300)) + fadeIn()
                    },
                    exitTransition = {
                        slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) + fadeOut()
                    },
                    popEnterTransition = {
                        slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300)) + fadeIn()
                    },
                    popExitTransition = {
                        slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(300)) + fadeOut()
                    }
                ) {
                    ProfileScreen()
                }

                composable(
                    route = NavRoute.Settings.route,
                    enterTransition = {
                        slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300)) + fadeIn()
                    },
                    exitTransition = {
                        slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) + fadeOut()
                    },
                    popEnterTransition = {
                        slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300)) + fadeIn()
                    },
                    popExitTransition = {
                        slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(300)) + fadeOut()
                    }
                ) {
                    SettingScreen()
                }
            }
        }
    }
}