package com.littlesoul.common.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.littlesoul.common.AppConstants.LOG_DEBUG
import com.littlesoul.ui.LittleSoulApplication.Companion.appContext


object ShowLogToast {


    fun ShowLog(message: String) {
        val LOG_TAG = ReusedMethod.getApplicationName(appContext) + "_LOG"

        if (LOG_DEBUG)
            Log.d(LOG_TAG, message)
    }


    fun ShowLog(tag: String, message: String) {
        if (LOG_DEBUG)
            Log.d(tag, message)
    }

    fun ShowLogError(tag: String, message: String) {
        if (LOG_DEBUG)
            Log.e(tag, message)
    }

    fun ShowLogInfo(tag: String, message: String) {
        if (LOG_DEBUG)
            Log.e(tag, message)
    }

    fun ShowToast(context: Context, message: String, time: Int) {
        if (time == 0) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }


}