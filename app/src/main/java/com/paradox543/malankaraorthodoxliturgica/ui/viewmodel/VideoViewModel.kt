package com.paradox543.malankaraorthodoxliturgica.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paradox543.malankaraorthodoxliturgica.domain.video.model.VideoListingModel
import com.paradox543.malankaraorthodoxliturgica.domain.video.repository.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val videoRepository: VideoRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _videoData = MutableStateFlow<List<VideoListingModel.Data>>(emptyList())
    val videoData: StateFlow<List<VideoListingModel.Data>> = _videoData

    init {
        getVideoListing()
    }

    private fun getVideoListing() {
        viewModelScope.launch {
            _isLoading.update { true }
            videoRepository.getVideoList()
                .onSuccess { listingModel ->
                    _isLoading.update { false }
                    _videoData.update { listingModel.data }
                }
                .onFailure { _isLoading.update { false } }
        }
    }
}