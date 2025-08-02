package com.application.expense_tracker_koin.balance.presentation

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: BalanceAction.kt
 */
sealed interface BalanceAction {

    data class OnBalanceChanged(val newBalance: Double) : BalanceAction
    data object OnBalanceSaved : BalanceAction
}