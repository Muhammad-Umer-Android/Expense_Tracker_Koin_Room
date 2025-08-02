package com.application.expense_tracker_koin.spending_details.di

import com.application.expense_tracker_koin.spending_details.domain.UpsertSpendingUseCase
import com.application.expense_tracker_koin.spending_details.presentation.SpendingDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: SpendingDetailsModule.kt
 */
val spendingDetailsModule = module {
    single { UpsertSpendingUseCase(get()) }
    viewModel { SpendingDetailsViewModel(get(),get(),get()) }
}