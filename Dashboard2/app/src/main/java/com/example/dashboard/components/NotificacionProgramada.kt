package com.example.dashboard.components

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.dashboard.MainActivity
import com.example.dashboard.R
import com.example.dashboard.utils.Constants.channelId

class NotificacionProgramada : BroadcastReceiver() {

    //Objeto o requiere ser instanciado de una clase
    companion object {
        const val NOTIFICATION_ID = 5
    }


    override fun onReceive(
        context: Context,
        intent: Intent?
    ) {
        crearNotificacion(context)
        TODO("Not yet implementated")
    }

    private fun crearNotificacion(context: Context) {

        val intent = Intent(
            context,
            MainActivity::class.java
        ).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }


        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        val notification =
            NotificationCompat.Builder(
                context,
                channelId
            )
                .setSmallIcon(R.drawable.ic_shopping_cart_24)
                .setContentTitle("¡Cita Agendada!")
                .setContentText("Se le informa que actual estado es: Reunión programada")
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(
                            "Se le informa que actual estado es: " +
                                    "Reunión programada. El siguiente código sera de " +
                                    "Google Meet para su reunion, muchas gracias " +
                                    "y buena suerte. MYU-LSK-AAL"
                        )
                ).setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        manager.notify(
            NOTIFICATION_ID,
            notification
        )

    }
}