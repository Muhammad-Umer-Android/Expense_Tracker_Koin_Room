package com.application.expense_tracker_koin.spending_details.presentation

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: SpendingDetailsAction.kt
 */
sealed interface SpendingDetailsAction {

    data class UpdateName(val newName: String): SpendingDetailsAction
    data class UpdatePrice(val newPrice: Double): SpendingDetailsAction
    data class UpdateKilograms(val newKilograms: Double): SpendingDetailsAction
    data class UpdateQuantity(val newQuantity: Double): SpendingDetailsAction

    data object SaveSpending: SpendingDetailsAction

}