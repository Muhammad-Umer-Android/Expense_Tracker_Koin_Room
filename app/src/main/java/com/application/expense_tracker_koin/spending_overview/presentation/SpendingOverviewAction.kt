package com.application.expense_tracker_koin.spending_overview.presentation

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: SpendingOverviewAction.kt
 */
sealed interface SpendingOverviewAction {
    data object LoadSpendingOverviewAndBalance: SpendingOverviewAction
    data class OnDateChange(val newDate: Int): SpendingOverviewAction
    data class OnDeleteSpending(val spendingId: Int): SpendingOverviewAction
    data class OnEditSpending(val editSpendingId: Int): SpendingOverviewAction
}