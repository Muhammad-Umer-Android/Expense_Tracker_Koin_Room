package com.application.expense_tracker_koin.balance.di

import com.application.expense_tracker_koin.balance.presentation.BalanceViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: BalanceModule.kt
 */
val balanceModule = module {
    viewModel { BalanceViewModel(get()) }
}