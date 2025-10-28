package com.example.randomduck_app.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.randomduck_app.viewModel.DuckViewModel

@Composable
fun DuckAppLayout(
    duckVM: DuckViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by duckVM.uiState.collectAsState()
    Surface(
        modifier = modifier
    ) {

    }
}