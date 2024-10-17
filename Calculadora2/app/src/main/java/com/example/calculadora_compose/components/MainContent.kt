package com.example.calculadora_compose.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat.ScrollAxis
import com.example.calculadora_compose.utils.sp

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    inputText: String,
    outputText: String,
    height: Dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.End
        ) {

            Row(
                modifier = Modifier.horizontalScroll(
                    ScrollState(0),
                    enabled = true,
                    reverseScrolling = true
                )
            ) {
                BasicTextField(
                    value = inputText,
                    onValueChange = { },
                    enabled = false,
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = height.sp() * 0.1,
                        textAlign = TextAlign.End
                    )
                )

            }
            Text(
                text = outputText,
                style = TextStyle(
                    color = Color.DarkGray,
                    fontSize = height.sp() * 0.27
                ),
                softWrap = false,
                maxLines = 1
            )

        }
    }


}
