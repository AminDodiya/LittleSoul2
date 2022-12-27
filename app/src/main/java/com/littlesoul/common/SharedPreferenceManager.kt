package com.littlesoul.common.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.littlesoul.common.AppConstants
import com.littlesoul.model.signup.SignUpRespData


object SharedPreferenceManager {
    val PREF_NAME = AppConstants.APP_NAME + "_pref"
    private var isInit = false
    private var prefs: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    @SuppressLint("CommitPrefEdits")
    fun init(context: Context) {
        if (isInit)
            return
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = prefs!!.edit()
        isInit = true
    }

    @SuppressLint("CommitPrefEdits")
    fun getUser(activity: Context): SignUpRespData? {
        if (!isInit) {
            prefs = activity.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            editor = prefs!!.edit()
            isInit = true
        }
        val userJson = getString(AppConstants.USER_DETAIL_LOGIN, "")

        if (userJson?.isEmpty()!!) {
            return null
        }
        try {
            return Gson().fromJson(userJson, SignUpRespData::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun putBoolean(key: String, value: Boolean) {
        editor!!.putBoolean(key, value)
        editor!!.commit()
    }

    fun putString(key: String, value: String) {
        editor!!.putString(key, value)
        editor!!.commit()
    }

    fun putFloat(key: String, value: Float) {
        editor!!.putFloat(key, value)
        editor!!.commit()
    }

    fun putLong(key: String, value: Long) {
        editor!!.putLong(key, value)
        editor!!.commit()
    }

    fun putInt(key: String, value: Int) {
        editor!!.putInt(key, value)
        editor!!.commit()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return prefs!!.getBoolean(key, defaultValue)
    }

    fun getString(key: String, defValue: String): String? {
        return prefs!!.getString(key, defValue)
    }

    fun getFloat(key: String, defValue: Float): Float {
        return prefs!!.getFloat(key, defValue)
    }

    fun getInt(key: String, defValue: Int): Int {
        return prefs!!.getInt(key, defValue)
    }

    fun getLong(key: String, defValue: Long): Long {
        return prefs!!.getLong(key, defValue)
    }

    fun readSharedPreferences(): SharedPreferences? {
        return prefs
    }

    @SuppressLint("LogNotTimber")
    fun removeAllData() {
        val keys: Map<String, *> = prefs!!.getAll()
        for ((key, value) in keys) {
            Log.d("map values", key + ": " + value.toString())
            if (key != AppConstants.SKIP_INTRO) {
                val editor: SharedPreferences.Editor = readSharedPreferences()!!.edit()
                editor.remove(key)
                editor.apply()
            }
        }
        // putBoolean(AppConstants.SKIP_INTRO, true)
    }

}
