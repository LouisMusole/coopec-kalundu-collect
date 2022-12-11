package com.rdcmind.coopeccollect.presentation

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rdcmind.coopeccollect.CollectMoneyActivity
import com.rdcmind.coopeccollect.presentation.dashboard.DashboardScreen
import com.rdcmind.coopeccollect.presentation.analyses.AnalyseScreen
import com.rdcmind.coopeccollect.presentation.membres.MembresScreen
import com.rdcmind.coopeccollect.presentation.transactions.TransactionsScreen
import com.rdcmind.coopeccollect.ui.theme.Bleu
import com.rdcmind.coopeccollect.util.CoopecScreen


@Composable
fun CoopecApp() {
    val context = LocalContext.current
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                BottomNavigationItem(
                    selectedContentColor = Color.Blue,
                    icon = {
                        if (currentDestination?.hierarchy?.any { it.route == CoopecScreen.Dashboard.route } == true)
                            Icon(Icons.Filled.Dashboard, contentDescription = null, tint = Bleu)
                        else
                            Icon(
                                Icons.Filled.Dashboard,
                                contentDescription = null,
                                tint = Color.DarkGray
                            )
                    },
                    label = {
                        Text(
                            text=stringResource(CoopecScreen.Dashboard.resourceId!!),
                            color = if (currentDestination?.hierarchy?.any { it.route == CoopecScreen.Dashboard.route } == true)
                                Bleu
                            else
                                Color.DarkGray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == CoopecScreen.Dashboard.route } == true,
                    onClick = {
                        navController.navigate(CoopecScreen.Dashboard.route) /*{
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }*/
                    }
                )
                BottomNavigationItem(
                    selectedContentColor = Color.Blue,
                    icon = {
                        if (currentDestination?.hierarchy?.any { it.route == CoopecScreen.Transactions.route } == true)
                            Icon(Icons.Filled.SwapHoriz, contentDescription = null, tint = Bleu)
                        else
                            Icon(
                                Icons.Filled.SwapHoriz,
                                contentDescription = null,
                                tint = Color.DarkGray
                            )
                    },
                    label = {
                        Text(
                            stringResource(CoopecScreen.Transactions.resourceId!!),
                            color = if (currentDestination?.hierarchy?.any { it.route == CoopecScreen.Transactions.route } == true)
                                Bleu
                            else
                                Color.DarkGray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == CoopecScreen.Transactions.route } == true,
                    onClick = {
                        navController.navigate(CoopecScreen.Transactions.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                /*BottomNavigationItem(
                    selectedContentColor = Color.Blue,
                    icon = {
                        if (currentDestination?.hierarchy?.any { it.route == CoopecScreen.Analyses.route } == true)
                            Icon(Icons.Filled.Analytics, contentDescription = null, tint = Bleu)
                        else
                            Icon(
                                Icons.Filled.Analytics,
                                contentDescription = null,
                                tint = Color.DarkGray
                            )
                    },
                    label = {
                        Text(
                            stringResource(CoopecScreen.Analyses.resourceId!!),
                            color = if (currentDestination?.hierarchy?.any { it.route == CoopecScreen.Analyses.route } == true)
                                Bleu
                            else
                                Color.DarkGray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == CoopecScreen.Analyses.route } == true,
                    onClick = {
                        navController.navigate(CoopecScreen.Analyses.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )*/
                BottomNavigationItem(
                    selectedContentColor = Color.Blue,
                    icon = {
                        if (currentDestination?.hierarchy?.any { it.route == CoopecScreen.Membres.route } == true)
                            Icon(Icons.Filled.Group, contentDescription = null, tint = Bleu)
                        else
                            Icon(
                                Icons.Filled.Group,
                                contentDescription = null,
                                tint = Color.DarkGray
                            )
                    },
                    label = {
                        Text(
                            stringResource(CoopecScreen.Membres.resourceId!!),
                            color = if (currentDestination?.hierarchy?.any { it.route == CoopecScreen.Membres.route } == true)
                                Bleu
                            else
                                Color.DarkGray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == CoopecScreen.Membres.route } == true,
                    onClick = {
                        navController.navigate(CoopecScreen.Membres.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )

            }
        },
        topBar = {
            TopAppBar() {
                Row() {
                    Text(
                        text = "  Coopec Kalundu",
                        fontWeight = FontWeight.Bold,
                        color = Bleu,
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement = Arrangement.End
                    ) {

                        BadgedBox(badge = {
                            Badge() {
                                Text(text = "12")
                            }
                        }, modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clickable { /*TODO*/ }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Notifications,
                                contentDescription = "Notifications",
                                tint = Color.DarkGray
                            )
                        }
                        Icon(
                            imageVector = Icons.Outlined.AccountCircle,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .clickable { /*TODO*/ },
                            contentDescription = "Profil",
                            tint = Color.DarkGray
                        )

                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                Intent(context,CollectMoneyActivity::class.java).also {
                    it.putExtra("collecteurID", (context as Activity).intent.getStringExtra("collecteurID"))
                    context.startActivity(it)
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = CoopecScreen.Dashboard.route,
            Modifier.padding(innerPadding)
        ) {
            composable(CoopecScreen.Dashboard.route) { DashboardScreen(navController) }
            composable(CoopecScreen.Transactions.route) { TransactionsScreen(navController) }
            //composable(CoopecScreen.Analyses.route) { AnalyseScreen(navController) }
            composable(CoopecScreen.Membres.route) { MembresScreen(navController) }
        }
    }
}