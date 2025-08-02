package com.application.expense_tracker_koin.spending_overview.presentation.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: DateFormater.kt
 */
@RequiresApi(Build.VERSION_CODES.O)
fun ZonedDateTime.formatDate(): String {
    return "$dayOfMonth-$monthValue-$year"
}