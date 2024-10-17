package com.example.cupcakes.ui.theme

import androidx.lifecycle.ViewModel
import com.example.cupcakes.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

//Precio para cada cupcake solo
private const val PRICE_PER_CUPCAKE = 3500.00

//Costo adicional para la fecha de orden en el mismo día
private const val PRICE_FOR_SAME_DAY_PICKUP = 2000.00

//[OrderViewModel] sostiene la informacion una orden de cupcake en terminos de cantidad, sabor y fecha de recogida, tambien como calcular el precio total basado en los detalles de la orden

class OrderViewModel : ViewModel() {
    //Estado del Cupcake para la orden
    private val _uiState = MutableStateFlow(
        OrderUiState(
            pickupOptions = pickupOptions()
        )
    )
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    //Se pone la cantidad [numberCupcakes] de cupcakes para el estado de esta orden y actualiza el precio
    fun setQuantity(numberCupcakes: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                quantity = numberCupcakes,
                price = calculatePrice(quantity = numberCupcakes)
            )
        }
    }

    //Pone el [desiredFlavor] de los cupcakes para este estado de orden. Solo 1 sabor puede ser seleccionado para la orden
    fun setFlavor(desiredFlavor: String) {
        _uiState.update { currentState ->
            currentState.copy(
                flavor = desiredFlavor
            )
        }
    }

    //Pone el [pickupDate] para el estado de la orden y actualiza el precio
    fun setDate(pickupDate: String) {
        _uiState.update { currentState ->
            currentState.copy(
                date = pickupDate,
                price = calculatePrice(pickupDate = pickupDate)
            )
        }
    }

    //Reinicia el estado de la orden
    fun resetOrder() {
        _uiState.value = OrderUiState(pickupOptions = pickupOptions())
    }

    //Vuelve al precio calculado basado en los detalles de la orden
    private fun calculatePrice(
        quantity: Int = _uiState.value.quantity,
        pickupDate: String = _uiState.value.date
    ): String {
        var calculatedPrice = quantity * PRICE_PER_CUPCAKE
        //Si el ususario selecciona la primera opción (Hoy) para recoger el pedido, se le suma un costo
        val todayOption = pickupOptions()[0].substringBefore(" at")
        if (todayOption == pickupDate.substringBefore(" at")) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        val formattedPrice = NumberFormat.getCurrencyInstance().format(calculatedPrice)
        return formattedPrice
    }


    //Vuele a la lista de fechas al iniciar con la fecha de ahora y con las 3 fechas siguientes
    private fun pickupOptions(): List<String> {
        val dateOptions = mutableListOf<String>()

        Locale.setDefault(
            Locale(
                "es", " CO "
            )
        )

        val formatter = SimpleDateFormat(
            "E, d MMMM 'at' hh:mm:ss", Locale.getDefault()
        )

        formatter.timeZone = TimeZone.getTimeZone("America/Bogota")

        val calendar = Calendar.getInstance()
        //añade los datos de los días después
        repeat(4) {
            dateOptions.add(formatter.format(calendar.time))
            calendar.add(
                Calendar.DATE, 1
            )
        }
        return dateOptions
    }
}