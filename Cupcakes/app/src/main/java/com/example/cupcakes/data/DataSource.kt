package com.example.cupcakes.data

import com.example.cupcakes.R

object DataSource {
    val flavors = listOf(
        R.string.Vainilla,
        R.string.Chocolate,
        R.string.Arequipe,
        R.string.Sated_caramel,
        R.string.Coffe,
    )
    val quantityOptions = listOf(
        Pair(R.string.one_cupcake, 1),
        Pair(R.string.six_cupcakes, 6),
        Pair(R.string.twelve_cupcakes, 12)
    )
}