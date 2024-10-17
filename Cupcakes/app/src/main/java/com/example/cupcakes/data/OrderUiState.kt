package com.example.cupcakes.data

//Clase de datos que representa el activo UI estado in terminos de [quantity], [flavor], [dataOptions], escoge el pickup [date] y [price]

data class OrderUiState(
    //Se escgoe la cantidad de Cupackes
    val quantity: Int = 0,

    //Sabor de los Cupcakes en la orden
    val flavor: String = "",

    //Escoge la fecha para recoger
    val date: String = "",

    //Total de predcio de la orden
    val price: String = "",

    //Fechas habilitadas para la orden
    val pickupOptions: List<String> = listOf()
)