package com.application.expense_tracker_koin.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.application.expense_tracker_koin.balance.presentation.BalanceScreenCore
import com.application.expense_tracker_koin.core.presentation.ui.theme.Expense_Tracker_koinTheme
import com.application.expense_tracker_koin.core.presentation.util.Background
import com.application.expense_tracker_koin.spending_details.presentation.SpendingDetailsScreenCore
import com.application.expense_tracker_koin.spending_overview.presentation.SpendingOverviewScreenCore
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Expense_Tracker_koinTheme {
                Navigation(modifier = Modifier.fillMaxSize())
            }
        }
    }


    @Composable
    fun Navigation(modifier: Modifier = Modifier) {
        val navController = rememberNavController()

        Background()

        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = Screen.SpendingOverview.route
        ) {

            composable(Screen.SpendingOverview.route) {
                SpendingOverviewScreenCore(
                    onBalanceClick = {
                        navController.navigate(Screen.Balance.route)
                    },
                    onAddSpendingClick = {
                        navController.navigate(Screen.SpendingDetails.createRoute(-1))
                    },
                    onEditSpendingClick = { id ->
                        navController.navigate(Screen.SpendingDetails.createRoute(id))
                    }
                )
            }

            composable(
                route = Screen.SpendingDetails.route,
                arguments = listOf(
                    navArgument("spendingId") {
                        type = NavType.IntType
                        defaultValue = -1
                    }
                )
            ) {
                SpendingDetailsScreenCore(
                    onSaveSpending = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Screen.Balance.route) {
                BalanceScreenCore(
                    onSaveClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

    }
}