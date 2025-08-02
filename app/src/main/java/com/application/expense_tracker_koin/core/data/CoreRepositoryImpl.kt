package com.application.expense_tracker_koin.core.data

import android.content.SharedPreferences
import com.application.expense_tracker_koin.core.domain.CoreRepository

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: CoreRepositoryImpl.kt
 */
class CoreRepositoryImpl(
    private val prefs: SharedPreferences
) : CoreRepository {

    override suspend fun updateBalance(balance: Double) {
        prefs.edit().putFloat(KEY_BALANCE, balance.toFloat()).apply()
    }

    override suspend fun getBalance(): Double {
        return prefs.getFloat(KEY_BALANCE, 0f).toDouble()
    }

    companion object {
        private const val KEY_BALANCE = "KEY_BALANCE"
    }
}