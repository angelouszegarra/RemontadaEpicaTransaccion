package com.example.remontadaepicatransaccion

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Log para depuración
        Log.d("FCM", "Mensaje recibido: ${remoteMessage.notification?.body}")

        // Mostrar notificación si existe
        remoteMessage.notification?.let {
            val title = it.title ?: "Notificación"
            val body = it.body ?: "Sin contenido"
            showNotification(title, body)
        }
    }

    private fun showNotification(title: String, message: String) {
        val channelId = "default_channel"

        // Crear el canal de notificación en Android 8.0 (Oreo) o superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Notificaciones Generales",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }

        // Crear la notificación
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification) // Reemplaza con un ícono válido en res/drawable
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        // Verificar permisos en Android 13+ antes de mostrar la notificación
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.e("FCM", "Permiso de notificaciones no concedido.")
                return
            }
        }

        // Mostrar la notificación
        NotificationManagerCompat.from(/* context = */ this).notify(1, notification)
    }
}
