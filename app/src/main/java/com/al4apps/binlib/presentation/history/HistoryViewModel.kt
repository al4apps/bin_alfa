package com.al4apps.binlib.presentation.history

import androidx.lifecycle.ViewModel
import com.al4apps.binlib.domain.usecases.FetchCardsFromDbUseCase

class HistoryViewModel(
    fetchCardsFromDbUseCase: FetchCardsFromDbUseCase
) : ViewModel() {

    val cards = fetchCardsFromDbUseCase.flow()

}