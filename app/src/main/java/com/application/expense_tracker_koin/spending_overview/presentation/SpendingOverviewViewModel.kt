package com.application.expense_tracker_koin.spending_overview.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.expense_tracker_koin.core.domain.CoreRepository
import com.application.expense_tracker_koin.core.domain.LocalSpendingDataSource
import com.application.expense_tracker_koin.core.domain.Spending
import com.application.expense_tracker_koin.spending_overview.presentation.util.randomColor
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: SpendingOverviewViewModel.kt
 */
class SpendingOverviewViewModel(
    private val spendingDataSource: LocalSpendingDataSource,
    private val coreRepository: CoreRepository
) : ViewModel() {

    var state by mutableStateOf(SpendingOverviewState())
        private set

    fun onAction(action: SpendingOverviewAction) {
        when (action) {
            SpendingOverviewAction.LoadSpendingOverviewAndBalance -> {
                loadSpendingListAndBalance()
            }

            is SpendingOverviewAction.OnDateChange -> {
                val newDate = state.dateList[action.newDate]
                viewModelScope.launch {
                    state = state.copy(
                        pickedDate = newDate,
                        spendingList = getSpendingListByDate(newDate)
                    )
                }
            }

            is SpendingOverviewAction.OnDeleteSpending -> {
                viewModelScope.launch {
                    spendingDataSource.deleteSpending(action.spendingId)
                    state = state.copy(
                        spendingList = getSpendingListByDate(state.pickedDate),
                        dateList = spendingDataSource.getAllDates(),
                        balance = coreRepository.getBalance() - spendingDataSource.getSpendBalance(),
                    )
                }
            }

            is SpendingOverviewAction.OnEditSpending -> {
                viewModelScope.launch {
                    state = state.copy(
                        spendingList = getSpendingListByDate(state.pickedDate),
                        dateList = spendingDataSource.getAllDates(),
                        balance = coreRepository.getBalance() - spendingDataSource.getSpendBalance(),
                        isDropDownMenuVisible = false
                    )
                    // Here you can navigate to the edit screen with the spending details
                    // For example, using a navigation component or similar
                }
            }
        }
    }

    private fun loadSpendingListAndBalance() {
        viewModelScope.launch {
            println("loadSpendingListAndBalance: ${coreRepository.getBalance()}")
            val allDates = spendingDataSource.getAllDates()

            state = state.copy(
                spendingList = getSpendingListByDate(
                    allDates.lastOrNull() ?: ZonedDateTime.now()
                ),
                balance = coreRepository.getBalance() - spendingDataSource.getSpendBalance(),
                pickedDate = allDates.lastOrNull() ?: ZonedDateTime.now(),
                dateList = allDates.reversed()
            )


        }
    }

    private suspend fun getSpendingListByDate(date: ZonedDateTime): List<Spending> {
        return spendingDataSource
            .getSpendingsByDate(date)
            .reversed()
            .map { it.copy(color = randomColor()) }
    }

}