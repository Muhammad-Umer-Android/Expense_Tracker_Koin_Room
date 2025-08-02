package com.application.expense_tracker_koin.core.domain

import java.time.ZonedDateTime

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: Spending.kt
 */
data class Spending(
    val spendingId: Int?,
    val name: String,
    val price: Double,
    val kilograms: Double,
    val quantity: Double,
    val dateTimeUtc: ZonedDateTime,
    val color : Int = 0
)