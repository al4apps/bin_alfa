package com.al4apps.binlib.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.al4apps.binlib.domain.models.CardModel
import com.al4apps.binlib.domain.usecases.FetchCardInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val fetchCardInfoUseCase: FetchCardInfoUseCase
) : ViewModel() {

    private val _cardInfo = MutableStateFlow<CardModel?>(null)
    val cardInfo: StateFlow<CardModel?> = _cardInfo

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun fetchBinInfo(bin: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _cardInfo.value = fetchCardInfoUseCase.execute(bin)
            } catch (t: Throwable) {
                Log.d("HomeViewModel", "Error fetching BIN info", t)
            } finally {
                _isLoading.value = false
            }
        }
    }
}