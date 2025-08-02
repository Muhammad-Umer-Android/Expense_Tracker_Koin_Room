package com.application.expense_tracker_koin.core.presentation

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: Screen.kt
 */
//sealed interface Screen {
//
//    @kotlinx.serialization.Serializable
//    data object SpendingOverview: Screen
//
//    @kotlinx.serialization.Serializable
//    data class SpendingDetails(val spendingId: Int = -1): Screen
//
//    @kotlinx.serialization.Serializable
//    data object Balance: Screen
//
//}

sealed class Screen(val route: String) {
    object SpendingOverview : Screen("spendingOverview")
    object Balance : Screen("balance")

    object SpendingDetails : Screen("spendingDetails/{spendingId}") {
        fun createRoute(spendingId: Int): String = "spendingDetails/$spendingId"
    }
}
