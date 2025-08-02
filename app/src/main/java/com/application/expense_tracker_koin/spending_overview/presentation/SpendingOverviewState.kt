package com.application.expense_tracker_koin.spending_overview.presentation

import com.application.expense_tracker_koin.core.domain.Spending
import java.time.ZonedDateTime

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: SpendingOverviewState.kt
 */
data class SpendingOverviewState (
    val spendingList: List<Spending> = emptyList(),
    val dateList: List<ZonedDateTime> = emptyList(),
    val balance: Double = 0.0,
    val pickedDate: ZonedDateTime = ZonedDateTime.now(),
    val isDropDownMenuVisible: Boolean = false,
)