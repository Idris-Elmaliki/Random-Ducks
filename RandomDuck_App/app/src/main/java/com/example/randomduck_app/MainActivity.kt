package com.example.randomduck_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.randomduck_app.data.network.Duck_API
import com.example.randomduck_app.data.repositories.DuckRepositories
import com.example.randomduck_app.ui.DuckAppLayout
import com.example.randomduck_app.ui.theme.RandomDuck_AppTheme
import com.example.randomduck_app.viewModel.DuckViewModel
import com.example.randomduck_app.viewModel.DuckViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            RandomDuck_AppTheme {
                val api = Retrofit.Builder()
                    .baseUrl("https://random-d.uk/api/random")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(Duck_API::class.java)
                val repository = DuckRepositories(api)
                val factory = DuckViewModelFactory(repository)
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
    }
}