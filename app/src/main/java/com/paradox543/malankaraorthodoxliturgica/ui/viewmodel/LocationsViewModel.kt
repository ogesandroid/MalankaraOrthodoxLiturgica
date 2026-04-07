package com.paradox543.malankaraorthodoxliturgica.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paradox543.malankaraorthodoxliturgica.domain.locations.model.LocationsModel
import com.paradox543.malankaraorthodoxliturgica.domain.locations.repository.LocationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val locationsRepository: LocationsRepository
) : ViewModel() {


    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _locationData = MutableStateFlow<List<LocationsModel.Data>>(emptyList())
    val locationData: StateFlow<List<LocationsModel.Data>> = _locationData


    fun getLocations(searchKey: String) {
        viewModelScope.launch {
            _isLoading.update { true }
            locationsRepository.getLocationsList(searchKey)
                .onSuccess { locationsModel ->
                    _isLoading.update { false }
                    if (locationsModel.data.isEmpty()) {
                        _error.update { locationsModel.message }
                        _locationData.update { emptyList() }
                    } else {
                        _error.update { null }
                        _locationData.update { locationsModel.data }
                    }
                }
                .onFailure {
                    _isLoading.update { false }
                    _error.update { "Something went wrong" }
                    _locationData.update { emptyList() }
                }
        }
    }

    /*fun clearLocations() {
        _locationData.value = emptyList()
    }*/

}