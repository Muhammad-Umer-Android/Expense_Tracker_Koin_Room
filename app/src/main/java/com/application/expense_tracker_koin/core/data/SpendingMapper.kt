package com.application.expense_tracker_koin.core.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.application.expense_tracker_koin.core.data.local.SpendingEntity
import com.application.expense_tracker_koin.core.domain.Spending
import java.time.Instant
import java.time.ZoneId

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: SpendingMapper.kt
 */

fun SpendingEntity.toSpending(): Spending = Spending(
    spendingId = spendingId ?: 0,
    name = name,
    price = price,
    kilograms = kilograms,
    quantity = quantity,
    dateTimeUtc = Instant.parse(dateTimeUtc).atZone(ZoneId.of("UTC"))
)

fun Spending.toNewSpendingEntity(): SpendingEntity = SpendingEntity(
    name = name,
    price = price,
    kilograms = kilograms,
    quantity = quantity,
    dateTimeUtc = dateTimeUtc.toInstant().toString()
)

fun Spending.toEditedSpendingEntity(): SpendingEntity = SpendingEntity(
    spendingId = spendingId,
    name = name,
    price = price,
    kilograms = kilograms,
    quantity = quantity,
    dateTimeUtc = dateTimeUtc.toInstant().toString()
)