package com.example.fcmtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import androidx.work.Worker

import javax.xml.transform.Result

class Worker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        notification()
        return Result.success()
    }

    private fun notification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "special channel"
            val descriptionText = "just a normal channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("20", name, importance)
            channel.description = descriptionText
            // Register the channel with the system
            val notificationManager: NotificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        var builder = NotificationCompat.Builder(appContext, "20")
            .setSmallIcon(R.drawable.ic_baseline_notifications_paused_24)
            .setContentTitle("My notification")
            .setContentText("Much longer text that cannot fit one line...")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Much longer text that cannot fit one line...")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        NotificationManagerCompat.from(this).notify(1, builder.build())
    }
}