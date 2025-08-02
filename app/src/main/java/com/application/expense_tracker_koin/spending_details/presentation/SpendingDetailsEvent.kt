package com.application.expense_tracker_koin.spending_details.presentation

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: SpendingDetailsEvent.kt
 */
sealed interface SpendingDetailsEvent {

    data object SaveSuccess: SpendingDetailsEvent
    data object SaveFailed: SpendingDetailsEvent

}