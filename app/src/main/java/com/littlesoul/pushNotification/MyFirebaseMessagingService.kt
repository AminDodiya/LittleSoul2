package com.stem.pushNotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.littlesoul.R
import com.littlesoul.common.AppConstants.DEVICE_ID
import com.littlesoul.common.utils.SharedPreferenceManager.putString
import com.littlesoul.common.utils.ShowLogToast.ShowLog

import org.json.JSONObject
import java.util.*


class MyFirebaseMessagingService : FirebaseMessagingService() {
    var TAG = "Firebase"
    var body: String? = null
    var title: String? = null
    var jobid: String? = null

    var NOTIFICATION_CHANNEL_ID: String = "101231";
    override fun onNewToken(s: String) {
        super.onNewToken(s)
        ShowLog(TAG, "Refreshed token: --> $s")
        putString(DEVICE_ID, s)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        ShowLog(TAG, "message--> data" + remoteMessage.data)

        val data = JSONObject(Objects.requireNonNull(remoteMessage.data) as Map<*, *>)

        sendNotification(data)
        sendBroadcastData(data)
    }

    private fun sendBroadcastData(data: JSONObject) {

        val intent = Intent("push")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    private fun sendNotification(data: JSONObject) {
       /* var intent: Intent? = null
        //intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0 *//* Request code *//*, intent,
            PendingIntent.FLAG_ONE_SHOT
        )
        body = data.optString("body")
        title = data.optString("title")
        val channelId = NOTIFICATION_CHANNEL_ID
        val soundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    this.getResources(),
                    R.mipmap.ic_launcher
                )
            )
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(soundUri)

            .setDefaults(Notification.DEFAULT_SOUND)
            .setContentIntent(pendingIntent)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            assert(notificationManager != null)
            notificationManager.createNotificationChannel(channel)
        }
        val notifId = System.currentTimeMillis().toInt()
        assert(notificationManager != null)
        notificationManager.notify(notifId, notificationBuilder.build())*/
    }
}