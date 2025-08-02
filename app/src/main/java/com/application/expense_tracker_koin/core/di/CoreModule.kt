package com.application.expense_tracker_koin.core.di

import android.content.Context
import androidx.room.Room
import com.application.expense_tracker_koin.core.data.CoreRepositoryImpl
import com.application.expense_tracker_koin.core.data.RoomSpendingDataSource
import com.application.expense_tracker_koin.core.data.local.SpendingDatabase
import com.application.expense_tracker_koin.core.domain.CoreRepository
import com.application.expense_tracker_koin.core.domain.LocalSpendingDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: CoreModule.kt
 */
val coreModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            SpendingDatabase::class.java,
            "spending_database_db"
        ).build()
    }

    single { get<SpendingDatabase>().dao }

    single {
        androidApplication().getSharedPreferences(
            "spending_tracker_preferences", Context.MODE_PRIVATE
        )
    }

    singleOf(::RoomSpendingDataSource).bind<LocalSpendingDataSource>()
    singleOf(::CoreRepositoryImpl).bind<CoreRepository>()
}