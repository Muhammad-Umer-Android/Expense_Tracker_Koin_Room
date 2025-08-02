package com.application.expense_tracker_koin.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: SpendingDatabase.kt
 */
@Database(
    entities = [SpendingEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SpendingDatabase: RoomDatabase() {
    abstract val dao: SpendingDao
}