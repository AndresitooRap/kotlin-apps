package com.example.calculadora_compose

sealed class CalculatorEvent {
    object BackSpace: CalculatorEvent()
    object AllClear: CalculatorEvent()
    object Calculate: CalculatorEvent()
    class Write(val value: String): CalculatorEvent()
}
