package com.application.expense_tracker_koin.core.domain

import java.time.ZonedDateTime

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: LocalSpendingDataSource.kt
 */
interface LocalSpendingDataSource {

    suspend fun getAllSpendings(): List<Spending>

    suspend fun getSpendingsByDate(
        dateTimeUtc: ZonedDateTime
    ): List<Spending>

    suspend fun getAllDates(): List<ZonedDateTime>

    suspend fun getSpending(id: Int): Spending

    suspend fun upsertSpending(spending: Spending)

    suspend fun getSpendBalance(): Double

    suspend fun deleteSpending(id: Int)

}