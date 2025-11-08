package com.example.randomduck_app.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.randomduck_app.data.repositories.DuckRepositories
import com.example.randomduck_app.viewModel.DuckViewModel

@Suppress("UNCHECKED_CAST")
class DuckViewModelFactory(
    private val repository: DuckRepositories
) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>) : T {
        if(modelClass.isAssignableFrom(DuckViewModel::class.java)) {
            return DuckViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}