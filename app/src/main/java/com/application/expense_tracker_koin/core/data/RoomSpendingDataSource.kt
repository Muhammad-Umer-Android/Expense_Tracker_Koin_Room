package com.application.expense_tracker_koin.core.data


import android.os.Build
import androidx.annotation.RequiresApi
import com.application.expense_tracker_koin.core.data.local.SpendingDao
import com.application.expense_tracker_koin.core.domain.LocalSpendingDataSource
import com.application.expense_tracker_koin.core.domain.Spending
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: RoomSpendingDataSource.kt
 */
class RoomSpendingDataSource(
    private val dao: SpendingDao
) : LocalSpendingDataSource {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAllSpendings(): List<Spending> {
        return dao.getAllSpendings().map { it.toSpending() }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getSpendingsByDate(
        dateTimeUtc: ZonedDateTime
    ): List<Spending> {
        return dao.getAllSpendings().map { it.toSpending() }
            .filter { spending ->
                spending.dateTimeUtc.dayOfMonth == dateTimeUtc.dayOfMonth
                        && spending.dateTimeUtc.month == dateTimeUtc.month
                        && spending.dateTimeUtc.year == dateTimeUtc.year
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAllDates(): List<ZonedDateTime> {
        val uniqueDates = mutableSetOf<LocalDate>()
        return dao.getAllDates()
            .map { Instant.parse(it).atZone(ZoneId.of("UTC")) }
            .filter {
                uniqueDates.add(it.toLocalDate())
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getSpending(id: Int): Spending {
        return dao.getSpending(id).toSpending()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun upsertSpending(spending: Spending) {
        dao.upsertSpending(
            if (spending.spendingId != null) {
                spending.toEditedSpendingEntity()
            } else
            spending.toNewSpendingEntity()
        )
    }

    override suspend fun getSpendBalance(): Double {
        return dao.getSpendBalance() ?: 0.0
    }

    override suspend fun deleteSpending(id: Int) {
        dao.deleteSpending(id)
    }
}