package com.sultan.pepulesaat.presentation.navigation

/*
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val screensWithBottomBar = listOf(
        Screen.FeedScreen,
        Screen.SearchScreen,
        Screen.CartScreen,
        Screen.FavoritesScreen
    )

    val currentRoute = navController.currentBackStackEntry?.destination?.route

    // Check if the current route is in the screensWithBottomBar list
    val showBottomBar = currentRoute in screensWithBottomBar.map { it.screenRoute }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomBar(navController = navController)
            }
        }
    ) {
        BottomNavGraph(navController = navController)
    }
}


@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        Screen.FeedScreen,
        Screen.SearchScreen,
        Screen.CartScreen,
        Screen.FavoritesScreen
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: Screen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            screen.selectedIcon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = "Navigation Icon"
                )
            }
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.screenRoute
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.screenRoute) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}
 */