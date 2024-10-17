package com.example.calculadora_compose

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context

class CalculatorViewModel : ViewModel() {

    private val _inputText = mutableStateOf(CalculatorState())
    val inputText: State<CalculatorState> = _inputText

    private val _outputText = mutableStateOf(CalculatorState())
    val outputText: State<CalculatorState> = _outputText

    private var expression = ""

    fun onEvent(e: CalculatorEvent) {
        when (e) {
            CalculatorEvent.AllClear -> allClear()
            CalculatorEvent.BackSpace -> backSpace()
            CalculatorEvent.Calculate -> calculate()
            is CalculatorEvent.Write -> write(e.value)
        }
    }

    private fun allClear() {
        _inputText.value = inputText.value.copy(
            text = ""
        )
        _outputText.value = outputText.value.copy(
            text = ""
        )
        expression = ""
    }

    private fun backSpace() {
        val length = _inputText.value.text.length
        if (length > 0) {
            _inputText.value = inputText.value.copy(
                text = inputText.value.text.subSequence(0, length - 1) as String
            )
            expression = expression.substring(0, expression.length - 1)
        }
    }

    private var previousResult = ""
    private var resultShown = false
    private fun calculate() {
        val balancedExpression = balanceParentheses(expression)
        previousResult = rhinoSetUp(balancedExpression)
        _outputText.value = outputText.value.copy(
            text = previousResult
        )
        _inputText.value = inputText.value.copy(
            text = previousResult
        )
        expression = previousResult

        // Establecer resultShown en true
        resultShown = true
    }

    private fun balanceParentheses(expression: String): String {
        val openingParenthesesCount = expression.count { it == '(' }
        val closingParenthesesCount = expression.count { it == ')' }
        val remainingParentheses = openingParenthesesCount - closingParenthesesCount

        val balancedExpression = expression + ")".repeat(remainingParentheses)
        return balancedExpression
    }

    private fun write(value: String) {
        _inputText.value = inputText.value.copy(
            text = inputText.value.text + value
        )
        expression += value
        _outputText.value = outputText.value.copy(
            text = ""
        )
    }

    private fun rhinoSetUp(input: String): String {
        try {
            var processedInput = input.replace("×", "*") // Reemplazar multiplicación explícita
            processedInput = processedInput.replace(
                "(",
                "*("
            ) // Insertar multiplicación antes de paréntesis abiertos
            processedInput = processedInput.replace(
                ")(",
                ")*("
            ) // Insertar multiplicación entre paréntesis consecutivos
            processedInput =
                processedInput.replace(")", ")") // Eliminar cualquier multiplicación redundante

            val rhino = Context.enter()
            rhino.optimizationLevel = -1

            val scriptable = rhino.initStandardObjects()
            return rhino.evaluateString(
                scriptable,
                processedInput,
                "javascript",
                1,
                null
            ).toString()
        } catch (e: Exception) {
            return "Error"
        }

    }
}
