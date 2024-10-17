package com.example.cupcakes.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.cupcakes.R
import com.example.cupcakes.data.OrderUiState
import com.example.cupcakes.ui.components.FormattedPriceLabel

//Este composable espera que [orderUiState] que represente el estado del orden [onCancelButtonClicked], lambda que triggers cancelando la orden y passed en la orden final a [onSendButtonClicked] lambda

@Composable
fun OrderSummaryScreen(
    orderUiState: OrderUiState,
    onCancelButtonClicked: () -> Unit,
    onSendButtonClicked: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val resources = LocalContext.current.resources

    val numberOfCupcakes = resources.getQuantityString(
        R.plurals.Cupcakes,
        orderUiState.quantity,
        orderUiState.quantity,
    )
    //Carga y formatea un recurso string con el parametro.
    val orderSummary = stringResource(
        R.string.Order_details,
        numberOfCupcakes,
        orderUiState.flavor,
        orderUiState.date,
        orderUiState.quantity
    )
    val newOrder = stringResource(R.string.New_cupcake_order)
    //Crea una lista de order summary en la pantalla
    val items = listOf(
        //Linea de suma 1: muestra la cantidad seleccionada
        Pair(stringResource(R.string.Quantity), numberOfCupcakes),
        //Linea de suma 2: muestra el sabor seleccionado
        Pair(stringResource(R.string.Flavor), orderUiState.flavor),
        //Linea de suma 3: muestra la fecha seleccionada
        Pair(stringResource(R.string.Order_date), orderUiState.date)
    )

    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceBetween) {
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.padding_small)
            )
        ) {
            items.forEach { item ->
                Text(item.first.uppercase())
                Text(text = item.second, fontWeight = FontWeight.Bold)
                Divider(thickness = dimensionResource(R.dimen.padding_small))
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
            FormattedPriceLabel(
                subtotal = orderUiState.price,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Row(
            modifier = Modifier
                .weight(1f, false)
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onSendButtonClicked(newOrder, orderSummary) }) {
                    Text(stringResource(R.string.Send))
                }
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick =  onCancelButtonClicked ) {
                    Text(stringResource(R.string.Cancel))
                }
            }
        }
    }
}