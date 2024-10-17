package com.example.senaexpress.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.senaexpress.R


@Composable
fun FAB() {
    val context = LocalContext.current

    FloatingActionButton(
        onClick = {
            val phoneNumber = "+573012717380"
            val message = "¡Hello, i wanna know more about the application!"

            val url = "https://wa.me/$phoneNumber?text=${Uri.encode(message)}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)

        },
        shape = CircleShape,
        containerColor = Color(0xFF47C856),
    ) {
        Box() {
            Image(
                painter = painterResource(id = R.drawable.whatsapppng),
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}