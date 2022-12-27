package com.littlesoul.common.extentions

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog


fun Context.showCustomDialog(
    layout: Int,
    isCancelable: Boolean = true,
    listener: (view: View, alertDialog: AlertDialog) -> Unit
) {
    val views = LayoutInflater.from(this).inflate(layout, null)
    val builder = AlertDialog.Builder(this)
    builder.setView(views)
    builder.setCancelable(isCancelable)
    val alertDialog = builder.create()
    alertDialog.setCancelable(isCancelable)
    alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    alertDialog.setOnShowListener {
        listener(views, alertDialog)
    }
    if (!alertDialog.isShowing) {
        alertDialog.show()
    }
}
