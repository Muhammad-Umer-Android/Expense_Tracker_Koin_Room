package com.application.expense_tracker_koin.spending_details.domain

import com.application.expense_tracker_koin.core.domain.LocalSpendingDataSource
import com.application.expense_tracker_koin.core.domain.Spending

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: UpsertSpendingUseCase.kt
 */
class UpsertSpendingUseCase(
    private val spendingDataSource: LocalSpendingDataSource
) {

    suspend operator fun invoke(spending: Spending): Boolean {

        if (spending.name.isBlank()) {
            return false
        }
        if (spending.price < 0) {
            return false
        }
        if (spending.kilograms < 0) {
            return false
        }
        if (spending.quantity < 0) {
            return false
        }

        spendingDataSource.upsertSpending(spending)
        return true
    }

}