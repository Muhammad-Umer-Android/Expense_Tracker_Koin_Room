package com.application.expense_tracker_koin.spending_overview.di

import com.application.expense_tracker_koin.spending_overview.presentation.SpendingOverviewViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: spendingOverviewModule.kt
 */
val spendingOverviewModule = module {
    viewModel { SpendingOverviewViewModel(get(), get()) }
}