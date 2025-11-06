package com.example.randomduck_app.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.randomduck_app.R
import com.example.randomduck_app.viewModel.DuckUiState
import com.example.randomduck_app.viewModel.DuckViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DuckErrorScreen(
    duckVM: DuckViewModel,
    uiState: DuckUiState,
    modifier: Modifier = Modifier
) {
    Log.e("DuckErrorScreen", "Error: ${uiState.error}")
    Card(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.small_padding))
    ) {
        Column(
            modifier = modifier
        ) {
            TooltipBox(
                positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                tooltip = {
                    PlainTooltip(
                        shape = MaterialTheme.shapes.small,
                        modifier = modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = uiState.error ?: "Unknown error",
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Start
                        )
                    }
                },
                state = rememberTooltipState(),
            ) {
                Text(
                    text = stringResource(R.string.error_title),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = dimensionResource(R.dimen.small_padding))
                )
            }
            Text(
                text = stringResource(R.string.error_message),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Start,
            )
            Button(
                onClick = {
                    duckVM.refreshDuck()
                },
                shape = MaterialTheme.shapes.small,
                colors = ButtonColors(
                    containerColor = Color(0xFF9CCC65),
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Black,
                    disabledContentColor = Color.White
                ),
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.xs_padding))
                    .align(Alignment.End)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = null,
                        modifier = Modifier
                            .size(dimensionResource(R.dimen.smaller_icon_size))
                    )
                    Text(
                        text = stringResource(R.string.error_reload),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = dimensionResource(R.dimen.xs_padding))
                    )
                }
            }
        }
    }
}