package com.paradox543.malankaraorthodoxliturgica.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paradox543.malankaraorthodoxliturgica.domain.counsellingRequests.model.CounsellingRequestsModel
import com.paradox543.malankaraorthodoxliturgica.domain.counsellingRequests.repository.CounsellingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounsellingViewModel @Inject constructor(
    private val counsellingRepository: CounsellingRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _counsellingData = MutableStateFlow<CounsellingRequestsModel.Data?>(null)
    val counsellingData: StateFlow<CounsellingRequestsModel.Data?> = _counsellingData


    init {
        getCounsellingData()
    }

    private fun getCounsellingData() {
        viewModelScope.launch {
            _isLoading.update { true }
            counsellingRepository.getCounsellingData()
                .onSuccess { counsellingRequestsModel ->
                    _isLoading.update { false }
                    _counsellingData.update { counsellingRequestsModel.data }
                }
                .onFailure { _isLoading.update { false } }
        }
    }
}