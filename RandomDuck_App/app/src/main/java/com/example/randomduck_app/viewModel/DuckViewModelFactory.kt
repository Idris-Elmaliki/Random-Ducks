package com.example.randomduck_app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.randomduck_app.data.repositories.DuckRepositories

class DuckViewModelFactory(
    private val repository: DuckRepositories
) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>) : T {
        if(modelClass.isAssignableFrom(DuckViewModel::class.java)) {
            return DuckViewModel(repository) as T
        }
        throw IllegalArgumentException("Unkown ViewModel Class")
    }
}