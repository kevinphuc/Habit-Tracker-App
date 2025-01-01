package com.example.habit_tracker_app.receivers

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.habit_tracker_app.utils.SettingsUtil

class DeviceBootUpReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if(intent == null) return

        if(intent.action == Intent.ACTION_BOOT_COMPLETED) SettingsUtil.startNotificationServiceIfEnabled(context)
    }
}