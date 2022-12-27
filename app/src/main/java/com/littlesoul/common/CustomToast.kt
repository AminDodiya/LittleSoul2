package com.littlesoul.common.extentions

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.littlesoul.R

object CustomToast {

    @SuppressLint("ShowToast")
    fun showToast(
        context: Context,
        toast_title: String,
        duration: Boolean,
        txtColor: Int,
        bgColor: Int,
        isBackGroundChange: Boolean
    ) {
        val toast: Toast
        if (duration) {
            toast = Toast.makeText(context, toast_title, Toast.LENGTH_LONG)
        } else {
            toast = Toast.makeText(context, toast_title, Toast.LENGTH_SHORT)
        }

        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val toastView = li.inflate(R.layout.toast_hint_layout, null)


        val text = toastView.findViewById<TextView>(R.id.txtToast)
        val ll = toastView.findViewById<LinearLayout>(R.id.ll_toast)
        // ImageView ivStatus = toastView.findViewById(R.id.ivStatus);
//        if (isBackGroundChange){
//            if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                ll.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.toast_rounded_red))
//            }else{
//                ll.background=ContextCompat.getDrawable(context,R.drawable.toast_rounded_red)
//            }
//        }
        text.text = toast_title
        text.setTextColor(txtColor)
        /* if (drawable != 0) {
            ivStatus.setVisibility(View.VISIBLE);
            ivStatus.setImageResource(drawable);

        }*/
        // text.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);

        /*if (postion) {
            toast.setGravity(Gravity.TOP, 0, 50);
        } else
            toast.setGravity(Gravity.BOTTOM, 0, 50);*/
        toast.setGravity(Gravity.BOTTOM, 0, 50)
        toast.view = toastView

        toast.show()
    }

}