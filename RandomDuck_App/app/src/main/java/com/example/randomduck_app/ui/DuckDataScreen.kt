package com.example.randomduck_app.ui

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.randomduck_app.R
import com.example.randomduck_app.viewModel.DuckUiState
import com.example.randomduck_app.viewModel.DuckViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DuckImage(
    duckData: DuckUiState,
    context : Context
) {
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp.dp

    Card(
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .background(color = Color(0xFF9CCC65))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(duckData.imageUrl)
                .crossfade(true)
                .allowHardware(true)
                .error(R.drawable.yes_it_did)
                .listener(
                    onSuccess = { _, result ->
                        Log.d("DuckImage", "Success: ${result.dataSource}")
                    },
                    onError = { _, result ->
                        Log.d("DuckImage", "Error: ${result.throwable}")
                    }
                )
                .build(),
            contentDescription = stringResource(R.string.duck_content_description),
            modifier = Modifier
                .padding(dimensionResource(R.dimen.containers_padding))
                .aspectRatio(1f),

            contentScale = ContentScale.Crop
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NextDuckButton(
    duckVM: DuckViewModel,
    duckData: DuckUiState,
    modifier: Modifier = Modifier
) {
    TooltipBox(
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = {
            PlainTooltip(
                shape = MaterialTheme.shapes.small,
                shadowElevation = dimensionResource(R.dimen.small_padding)
            ) {
                Text(
                    text = duckData.duckMessage ?: "Loading...",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                )
            }
        },
        state = rememberTooltipState()
    ) {
        Card(
            onClick = {
                duckVM.refreshDuck()
            },
            modifier = modifier
                .padding(dimensionResource(R.dimen.containers_padding))
        ) {
            val config = LocalConfiguration.current


            Text(
                text = stringResource(R.string.loadNewImage),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.small_padding))
            )
        }
    }
}