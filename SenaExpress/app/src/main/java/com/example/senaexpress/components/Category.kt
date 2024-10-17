package com.example.senaexpress.components

import androidx.annotation.DrawableRes

data class Category(
    @DrawableRes val imgCategory: Int,
    val textCategory: Int,
    val isSelected: Boolean = false,
)
