package com.example.senaexpress.components

import androidx.annotation.DrawableRes

data class Product(
    @DrawableRes val Profile_image: Int,
    val Profile_text: Int,
    val Price: Int,
    @DrawableRes val Product_image: Int,
    val Product_text: Int,

    )
