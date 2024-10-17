package com.example.dashboard.components

import android.Manifest
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.dashboard.MainActivity
import com.example.dashboard.R
import com.example.dashboard.components.NotificacionProgramada.Companion.NOTIFICATION_ID
import java.util.Calendar

fun CreateChannelNotification(
    idChannel: String,
    context: Context,
    name: String,
    descriptionText: String
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        //Definicion del canal
        val channel =
            NotificationChannel(
                idChannel,
                name,
                importance
            ).apply {
                description = descriptionText
            }

        //Definición del Gesto de Notificaciones
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //Creación del canal
        notificationManager.createNotificationChannel(channel)
    }
}

fun notificacionSencilla(
    context: Context,
    idChannel: String,
    idNotification: Int,
    textTitle: String,
    textContent: String,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
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

    val builder =
        NotificationCompat.Builder(
            context,
            idChannel
        ).setSmallIcon(R.drawable.ic_shopping_cart_24)
            .setContentTitle(textTitle)
            .setContentText(textContent)
            .setPriority(priority)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //TODO: Consider Calling
            // ActivityCompat#requiestPermissions
            // here to request the missing permissions, and then overriding
            // public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                        int[] grantResults)
            //to handle the case where the user grants the permission. See the documentation
            //for ActivityCompat#requestPermissions for more details
            return
        }
        notify(idNotification, builder)
    }
}

fun notificacionExtensa(
    context: Context,
    idChannel: String,
    idNotification: Int,
    textTitle: String,
    textContent: String,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    var builder =
        NotificationCompat.Builder(context, idChannel)
            .setSmallIcon(R.drawable.ic_shopping_cart_24)
            .setContentTitle(textTitle)
            .setContentText(textContent)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(textContent)
            )
            .setPriority(priority)
            .build()


    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //TODO: Consider Calling
            // ActivityCompat#requiestPermissions
            // here to request the missing permissions, and then overriding
            // public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                        int[] grantResults)
            //to handle the case where the user grants the permission. See the documentation
            //for ActivityCompat#requestPermissions for more details
            return
        }
        notify(idNotification, builder)
    }

}

fun notificacionImage(
    context: Context,
    idChannel: String,
    idNotification: Int,
    textTitle: String,
    textContent: String,
    bigImagen: Bitmap,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
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

    val builder =
        NotificationCompat.Builder(
            context,
            idChannel
        ).setSmallIcon(R.drawable.ic_shopping_cart_24)
            .setContentTitle(textTitle)
            .setContentText(textContent)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(bigImagen)
                    .setBigContentTitle("Tienda Sena CBA")
            ).setPriority(priority)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //TODO: Consider Calling
            // ActivityCompat#requiestPermissions
            // here to request the missing permissions, and then overriding
            // public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                        int[] grantResults)
            //to handle the case where the user grants the permission. See the documentation
            //for ActivityCompat#requestPermissions for more details
            return
        }
        notify(idNotification, builder)
    }

}


fun notificacionProgramada(
    context: Context,
) {
    //Intent describe la actividad que se ebe iniciar y contiene los datos necesarion para ello
    val intent = Intent(
        context,
        MainActivity::class.java
    ).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    /* Un PendingIntent es una referencia a un token que mantiene el sistema.
        La aplicacion A puede pasar un PendingIntent a la aplicacion B para permitir
        que la aplicacion B ejecute acciones predefinidas en un nombre de la aplicacion A,
        independientemente de si esta sige activa
    */
    val pendingIntent: PendingIntent =
        PendingIntent.getActivity(
            context,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.setExact(
        AlarmManager.RTC_WAKEUP,
        Calendar.getInstance().timeInMillis + 9000,
        pendingIntent
    )

}