package com.example.calculadora_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.calculadora_compose.components.MainContent
import com.example.calculadora_compose.components.Panel
import com.example.calculadora_compose.ui.theme.Calculadora_ComposeTheme

@Composable
fun CalculatorApp(
    viewModel: CalculatorViewModel
) {
    val inputState = viewModel.inputText.value
    val outputState = viewModel.outputText.value

    Calculadora_ComposeTheme() {
        CalculatorScreen(
            inputText = inputState.text,
            outputText = outputState.text,
            onEvent = { viewModel.onEvent(it) }
        )
    }
}

@Composable
fun CalculatorScreen(
    inputText: String,
    outputText: String,
    onEvent: (CalculatorEvent) -> Unit
) {
    BoxWithConstraints {
        val boxHeight = this.maxHeight
        val boxWidth = this.maxWidth

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            MainContent(
                inputText = inputText,
                outputText = outputText,
                height = boxHeight * 0.35f
            )
            Row {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 10.dp)
                ) { Divider(color = Color.Black, modifier = Modifier.height(2.dp)) }
            }
            Panel(
                height = boxHeight * 0.6f,
                width = boxWidth,
                onEvent = onEvent
            )
        }
    }
}