package com.example.randomduck_app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.randomduck_app.R
import kotlinx.coroutines.delay

@Composable
fun DuckLoadingScreen(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.small_padding))
            )
            Text(
                text = stringResource(R.string.please_hold_text),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.small_padding))
            )
            var dotCount by remember { mutableStateOf(0) };
            LaunchedEffect(Unit) {
                while(true) {
                    delay(750)
                    dotCount = (dotCount % 3) + 1
                }
            }
            Text(
                text = stringResource(R.string.loadingText) + ".".repeat(dotCount),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}