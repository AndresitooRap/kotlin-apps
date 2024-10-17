package com.example.cupcakes.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.cupcakes.R

//Composable que muestra el formato [price] que  sera formateado y mostrado en la pantalla

@Composable
fun FormattedPriceLabel(subtotal: String, modifier: Modifier = Modifier) {
    Column() {
        Text(
            text = stringResource(R.string.Subtotal_price, subtotal),
            modifier = modifier,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}