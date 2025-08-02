package com.application.expense_tracker_koin.core.domain

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: CoreRepository.kt
 */
interface CoreRepository {
    suspend fun updateBalance(balance: Double)
    suspend fun getBalance(): Double
}