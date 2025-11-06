package com.example.randomduck_app.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import com.example.randomduck_app.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.randomduck_app.viewModel.DuckUiState
import com.example.randomduck_app.viewModel.DuckViewModel
import kotlin.random.Random

@Composable
fun DuckAppLayout(
    duckVM: DuckViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by duckVM.uiState.collectAsState()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            DuckTopBar(
                modifier = Modifier
                    .safeContentPadding()
            )
        },
        modifier = modifier
    ) { innerpadding ->
        Log.d("DuckApp", "Image URL: ${uiState.imageUrl}, Message: ${uiState.duckMessage}")
        when {
            uiState.loadingStatus -> {
                DuckLoadingUi(
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.containers_padding))
                )
            }

            uiState.error != null -> {
                LaunchedEffect(uiState) {
                    val soundID = R.raw.error_duck_quack_spongebob
                    playDuckAudio(
                        context = context,
                        soundRes = soundID
                    )
                }

                DuckErrorUi(
                    duckVM = duckVM,
                    uiState = uiState,
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.containers_padding))
                )
            }

            !uiState.duckMessage.isNullOrBlank() && !uiState.imageUrl.isNullOrBlank() -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(innerpadding)
                        .padding(dimensionResource(R.dimen.containers_padding))
                        .fillMaxSize()
                ) {
                    DuckImage(
                        duckData = uiState,
                        context = LocalContext.current
                    )
                    NextDuckButton(
                        duckVM = duckVM,
                        duckData = uiState
                    )
                }

                LaunchedEffect(uiState) {
                    val soundID = R.raw.success_meme_duck_quack

                    playDuckAudio(
                        context = context,
                        soundRes = soundID
                    )
                }
            }

            else -> {
                throw Exception(stringResource(R.string.exception_message))
            }
        }
    }
}


@SuppressLint("AutoboxingStateCreation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DuckTopBar(
    modifier: Modifier = Modifier
) {
    var num by remember { mutableStateOf(Random.nextInt(0, 3)) }
    val soundRes = chooseDuckAudio(num)

    var clicked by remember { mutableStateOf(0) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF9CCC65))
    ) {
        Spacer(
            modifier = modifier
                .size(dimensionResource(R.dimen.icon_size))
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .weight(1f)
        ) {
            Text(
                text = stringResource(R.string.topBarText),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .wrapContentWidth()
            )
        }
        TooltipBox(
            positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
            tooltip = {
                PlainTooltip(
                    shape = MaterialTheme.shapes.small,
                    shadowElevation = dimensionResource(R.dimen.small_padding)
                ) {
                    Text(
                        text = stringResource(R.string.playButtonText),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }
            },
            state = rememberTooltipState()
        ) {
            IconButton(
                onClick = {
                    clicked++
                },
                modifier = modifier
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = stringResource(R.string.playButtonText)
                )
            }
        }
        val context = LocalContext.current
        LaunchedEffect(clicked) {
            if(clicked >= 1) {
                playDuckAudio(
                    context = context,
                    soundRes = soundRes
                )
                num = Random.nextInt(0, 3)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DuckLoadingUi(
    modifier: Modifier = Modifier
) {
    BasicAlertDialog(
        onDismissRequest = {},
        content = {
            DuckLoadingScreen(
                modifier = modifier
            )
        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DuckErrorUi(
    duckVM: DuckViewModel,
    uiState: DuckUiState,
    modifier: Modifier = Modifier
) {
    BasicAlertDialog(
        onDismissRequest = {
            duckVM.refreshDuck()
        },
        content = {
            DuckErrorScreen(
                duckVM = duckVM,
                uiState = uiState,
                modifier = modifier
            )
        },
        modifier = modifier
    )
}