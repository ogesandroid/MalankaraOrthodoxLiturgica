package com.paradox543.malankaraorthodoxliturgica.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paradox543.malankaraorthodoxliturgica.domain.heirarchy.model.HierarchyListModel
import com.paradox543.malankaraorthodoxliturgica.domain.heirarchy.repository.HierarchyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HierarchyViewModel @Inject constructor(
    private val hierarchyRepository: HierarchyRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _hierarchyData = MutableStateFlow<List<HierarchyListModel.Data>>(emptyList())
    val hierarchyData: StateFlow<List<HierarchyListModel.Data>> = _hierarchyData


    init {
        getHierarchyList()
    }

    private fun getHierarchyList() {
        viewModelScope.launch {
            _isLoading.update { true }
            hierarchyRepository.getHierarchyList()
                .onSuccess { hierarchyListModel ->
                    _isLoading.update { false }
                    _hierarchyData.update { hierarchyListModel.data }
                }
                .onFailure {
                    _isLoading.update { false }
                }
        }
    }
}