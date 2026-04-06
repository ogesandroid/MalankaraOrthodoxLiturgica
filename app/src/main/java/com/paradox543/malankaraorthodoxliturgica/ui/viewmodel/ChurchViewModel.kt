package com.paradox543.malankaraorthodoxliturgica.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paradox543.malankaraorthodoxliturgica.domain.church.model.ChurchListModel
import com.paradox543.malankaraorthodoxliturgica.domain.church.repository.ChurchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChurchViewModel @Inject constructor(
    private val churchRepository: ChurchRepository
) : ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _churchData = MutableStateFlow<List<ChurchListModel.Data>>(emptyList())
    val churchData: StateFlow<List<ChurchListModel.Data>> = _churchData


    init {
        viewModelScope.launch {
            getChurchInfo()
        }
    }

    private fun getChurchInfo() {
        _isLoading.update { true }
        viewModelScope.launch {
            churchRepository.getChurchInfo()
                .onSuccess { churchListModel ->
                    _isLoading.update { false }
                    _churchData.update { churchListModel.data }
                }
                .onFailure {
                    _isLoading.update { false }
                }
        }
    }
}