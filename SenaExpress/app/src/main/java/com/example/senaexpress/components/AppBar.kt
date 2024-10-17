package com.example.senaexpress.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.senaexpress.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {

    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box() {
                    Image(
                        painter = painterResource(R.drawable.logo_sena),
                        contentDescription = "Logo",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                        modifier = Modifier.size(40.dp)
                    )
                }

                Text(
                    "SenaExpress",
                    modifier = Modifier.padding(start = 5.dp),
                    style = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 23.sp)
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.ReceiptLong, contentDescription = null)
            }
        }


    )
    Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f))


}