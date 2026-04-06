package com.paradox543.malankaraorthodoxliturgica.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paradox543.malankaraorthodoxliturgica.domain.institution.model.InstitutionListModel
import com.paradox543.malankaraorthodoxliturgica.domain.institution.repository.InstitutionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InstitutionViewModel @Inject constructor(
    private val institutionRepository: InstitutionRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _institutionData = MutableStateFlow<List<InstitutionListModel.Data>>(emptyList())
    val institutionData: StateFlow<List<InstitutionListModel.Data>> = _institutionData

    init {
        viewModelScope.launch {
            getInstitutionInfo()
        }
    }

    private fun getInstitutionInfo() {
        viewModelScope.launch {
            _isLoading.update { true }
            institutionRepository.getInstitutionList()
                .onSuccess { institutionListModel ->
                    _isLoading.update { false }
                    _institutionData.update { institutionListModel.data }
                }
                .onFailure {
                    _isLoading.update { false }
                }
        }
    }

}