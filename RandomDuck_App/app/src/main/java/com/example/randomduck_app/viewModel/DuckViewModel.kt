package com.example.randomduck_app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomduck_app.data.repositories.DuckRepositories
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DuckUiState(
    val imageUrl: String? = null,
    val duckMessage: String? = null,
    val loadingStatus: Boolean = false,
    val error: String? = null
)
class DuckViewModel(
    private val repository : DuckRepositories
) : ViewModel() {

    private var _uiState = MutableStateFlow(DuckUiState())
    val uiState: StateFlow<DuckUiState> = _uiState.asStateFlow()

    fun getDuck() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loadingStatus = true, error = null)
            try {
                val randImage = repository.getRandomPic()

                _uiState.value = DuckUiState(
                    imageUrl = randImage.url,
                    duckMessage = randImage.message,
                    loadingStatus = false,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    loadingStatus = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }

    init {
        getDuck()
    }

    fun refreshDuck() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loadingStatus = true, error = null)
            try {
                val randImage = repository.getRandomPic()

                _uiState.value = _uiState.value.copy(
                    imageUrl = randImage.url,
                    loadingStatus = false,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    loadingStatus = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }
}