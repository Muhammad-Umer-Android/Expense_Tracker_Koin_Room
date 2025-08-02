package com.application.expense_tracker_koin.spending_details.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.expense_tracker_koin.core.domain.LocalSpendingDataSource
import com.application.expense_tracker_koin.core.domain.Spending
import com.application.expense_tracker_koin.spending_details.domain.UpsertSpendingUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

/**
 * @Author: Umer Dev
 * @Created: 01/08/2025
 * @File: SpendingDetailsViewModel.kt
 */
class SpendingDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val spendingDataSource: LocalSpendingDataSource,
    private val upsertSpendingUseCase: UpsertSpendingUseCase
) : ViewModel() {

    var state by mutableStateOf(SpendingDetailsState())
        private set

    private val _eventChannel = Channel<SpendingDetailsEvent>()
    val event = _eventChannel.receiveAsFlow()

    private val spendingId: Int = savedStateHandle["spendingId"] ?: -1

    init {
        if (spendingId != -1) {
            fetchSpending(spendingId)
        }
    }


    fun onAction(action: SpendingDetailsAction) {
        when (action) {
            is SpendingDetailsAction.UpdateKilograms -> {
                state = state.copy(
                    kilograms = action.newKilograms
                )
            }

            is SpendingDetailsAction.UpdateName -> {
                state = state.copy(
                    name = action.newName
                )
            }

            is SpendingDetailsAction.UpdatePrice -> {
                state = state.copy(
                    price = action.newPrice
                )
            }

            is SpendingDetailsAction.UpdateQuantity -> {
                state = state.copy(
                    quantity = action.newQuantity
                )
            }

            SpendingDetailsAction.SaveSpending -> {
                viewModelScope.launch {
                    if (saveSpending()) {
                        _eventChannel.send(SpendingDetailsEvent.SaveSuccess)
                    } else {
                        _eventChannel.send(SpendingDetailsEvent.SaveFailed)
                    }
                }
            }
        }
    }

    private suspend fun saveSpending(): Boolean {
        val spending = Spending(
            spendingId = if (spendingId != -1) spendingId else null,
            name = state.name,
            price = state.price,
            kilograms = state.kilograms,
            quantity = state.quantity,
            dateTimeUtc = if (spendingId != -1) state.dateTimeUtc ?: ZonedDateTime.now() else ZonedDateTime.now()
        )

        return upsertSpendingUseCase(spending)
    }

    private fun fetchSpending(id: Int) {
        viewModelScope.launch {
            val spending = spendingDataSource.getSpending(id)
            println("Fetched spending: $spending")
            state = state.copy(
                spendingId = spending.spendingId,
                name = spending.name,
                price = spending.price,
                kilograms = spending.kilograms,
                quantity = spending.quantity,
                dateTimeUtc = spending.dateTimeUtc,
            )
        }
    }


}