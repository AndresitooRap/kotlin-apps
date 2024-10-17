package com.example.calculadora_compose.utils

fun isNumber(s: String): Boolean {
    return if (s.isEmpty()) false else s.all { Character.isDigit(it) || s == "." }
}