package com.example.randomduck_app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomduck_app.data.repositories.DuckRepositories
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DuckViewModel(private val repository : DuckRepositories) : ViewModel() {
    data class DuckUiState(
        val imageUrl : String? = null,
        val duckFact : String? = null,
        val loadingStatus : Boolean = false,
        val error : String? = null
    )

    private var _uiState = MutableStateFlow(DuckUiState())
    val uiState : StateFlow<DuckUiState> = _uiState.asStateFlow()

    fun refreshDuck() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loadingStatus = true, error = null)
            try {
                val funFact = repository.getRandomFact()
                val randImage = repository.getRandomPic()

                _uiState.value = DuckUiState(
                    imageUrl = randImage.url,
                    duckFact = funFact.fact,
                    loadingStatus = false,
                    error = null
                )
            }
            catch(e : Exception) {
                _uiState.value = _uiState.value.copy(
                    loadingStatus = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }
}