package com.application.expense_tracker_koin.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: SpendingEntity.kt
 */
@Entity
data class SpendingEntity(

    @PrimaryKey(autoGenerate = true)
    val spendingId: Int? = null,

    val name: String,
    val price: Double,
    val kilograms: Double,
    val quantity: Double,
    val dateTimeUtc: String
)