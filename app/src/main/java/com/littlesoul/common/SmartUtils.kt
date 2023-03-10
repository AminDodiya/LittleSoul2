package com.tajmeel._socialmedia

import android.util.Log

import java.util.regex.Matcher
import java.util.regex.Pattern

object SmartUtils {

    private val TAG = "SmartUtil"

    fun emailValidator(mailAddress: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher

        val EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

        pattern = Pattern.compile(EMAIL_PATTERN)
        matcher = pattern.matcher(mailAddress)
        return matcher.matches()
    }

    fun checkPersonValidation(personName: String): Boolean {
        val regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]")
        if (regex.matcher(personName).find()) {
            Log.d(TAG, "SPECIAL CHARS FOUND")
            return true
        } else {
            return false
        }
    }

    fun checkSpecialPasswordValidation(password: String?): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_])(?=\\S+$).{6,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password!!)
        Log.e("PasswordValidated", "--->" + matcher.matches())
        return matcher.matches()
    }
}
