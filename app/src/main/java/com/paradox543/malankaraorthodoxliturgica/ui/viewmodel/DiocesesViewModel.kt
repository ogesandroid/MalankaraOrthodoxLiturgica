package com.paradox543.malankaraorthodoxliturgica.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paradox543.malankaraorthodoxliturgica.domain.dioceses.model.DiocesesListModel
import com.paradox543.malankaraorthodoxliturgica.domain.dioceses.repository.DiocesesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiocesesViewModel @Inject constructor(
    private val diocesesRepository: DiocesesRepository
): ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _diocesesData = MutableStateFlow<List<DiocesesListModel.Data>>(emptyList())
    val diocesesData: StateFlow<List<DiocesesListModel.Data>> = _diocesesData


    init {
        viewModelScope.launch {
            getDioceses()
        }
    }


    private fun getDioceses() {
        _isLoading.update { true }
        viewModelScope.launch {
            diocesesRepository.getDiocesesList()
                .onSuccess {diocesesListModel ->
                    _isLoading.update { false }
                    _diocesesData.update { diocesesListModel.data }
                }
                .onFailure {
                    _isLoading.update { false }
                }
        }
    }
}