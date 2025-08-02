package com.application.expense_tracker_koin.spending_details.presentation

import java.time.ZonedDateTime

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: SpendingDetailsState.kt
 */
data class SpendingDetailsState(
    val spendingId: Int? = null,
    val name: String = "",
    val price: Double = 0.0,
    val kilograms: Double = 0.0,
    val quantity: Double = 0.0,
    val dateTimeUtc: ZonedDateTime? = null
)