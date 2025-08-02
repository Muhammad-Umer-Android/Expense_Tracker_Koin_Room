package com.application.expense_tracker_koin

import android.app.Application
import com.application.expense_tracker_koin.balance.di.balanceModule
import com.application.expense_tracker_koin.core.di.coreModule
import com.application.expense_tracker_koin.spending_details.di.spendingDetailsModule
import com.application.expense_tracker_koin.spending_overview.di.spendingOverviewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: App.kt
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                coreModule,
                balanceModule,
                spendingOverviewModule,
                spendingDetailsModule
            )
        }

    }

}