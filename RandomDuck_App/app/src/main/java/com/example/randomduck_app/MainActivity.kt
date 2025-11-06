package com.example.randomduck_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.randomduck_app.data.RetrofitInstance
import com.example.randomduck_app.data.repositories.DuckRepositories
import com.example.randomduck_app.ui.DuckAppLayout
import com.example.randomduck_app.ui.theme.RandomDuck_AppTheme
import com.example.randomduck_app.viewModel.DuckViewModel
import com.example.randomduck_app.factories.DuckViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val repository = DuckRepositories(RetrofitInstance.api)
            val factory = DuckViewModelFactory(repository)
            RandomDuck_AppTheme {
                val duckVM : DuckViewModel = viewModel(
                    modelClass = DuckViewModel::class.java,
                    factory = factory
                )
                DuckAppLayout(
                    duckVM = duckVM,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Testing"
)
@Composable
fun DuckAppPreview() {
    val repository = DuckRepositories(RetrofitInstance.api)
    val factory = DuckViewModelFactory(repository)
    RandomDuck_AppTheme {
        val duckVM : DuckViewModel = viewModel(
            modelClass = DuckViewModel::class.java,
            factory = factory
        )
        DuckAppLayout(
            duckVM = duckVM,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}