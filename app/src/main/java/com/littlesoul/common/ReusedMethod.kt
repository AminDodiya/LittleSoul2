package com.littlesoul.common.utils

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Build
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.littlesoul.R
import com.littlesoul.common.AppConstants.PERMISSION_LOCATION_REQUEST_CODE
import com.littlesoul.common.utils.ShowLogToast.ShowLog

import java.text.SimpleDateFormat
import java.util.*


class ReusedMethod {
    internal var alertDialog: AlertDialog? = null

    companion object {
//        var fields: List<Place.Field> = Arrays.asList(
//                Place.Field.ADDRESS,
//                Place.Field.ADDRESS_COMPONENTS,
//                Place.Field.ID,
//                Place.Field.LAT_LNG,
//                Place.Field.NAME,
//                Place.Field.OPENING_HOURS,
//                Place.Field.PHONE_NUMBER,
//                Place.Field.PHOTO_METADATAS,
//                Place.Field.PLUS_CODE,
//                Place.Field.PRICE_LEVEL,
//                Place.Field.RATING,
//                Place.Field.TYPES,
//                Place.Field.USER_RATINGS_TOTAL,
//                Place.Field.UTC_OFFSET,
//                Place.Field.VIEWPORT,
//                Place.Field.WEBSITE_URI)

//        var CurrentPlacefields: List<Place.Field> = Arrays.asList(
//                Place.Field.ADDRESS,
//                Place.Field.ID,
//                Place.Field.LAT_LNG,
//                Place.Field.NAME,
//                Place.Field.PHOTO_METADATAS,
//                Place.Field.PLUS_CODE,
//                Place.Field.PRICE_LEVEL,
//                Place.Field.RATING,
//                Place.Field.TYPES,
//                Place.Field.USER_RATINGS_TOTAL,
//                Place.Field.VIEWPORT)

        internal fun getApplicationName(context: Context): String {
            val applicationInfo = context.applicationInfo
            val stringId = applicationInfo.labelRes
            return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else context.getString(
                stringId
            )
        }

        fun Activity.makeStatusBarTransparent() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.apply {
                    clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        decorView.systemUiVisibility =
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    } else {
                        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    }
                    statusBarColor = Color.TRANSPARENT
                }
            }
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun updateStatusBarColor(context: Activity, color: Int, position: Int) {
            // Color must be in hexadecimal fromat
            val window = context.window
            val view = window.decorView

            if (position == 0 || position == 2) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    view.systemUiVisibility =
                        view.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }

            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    window.clearFlags(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
                }
                view.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE

            }
            //        window.setStatusBarColor(context.getResources().getColor(color, context.getTheme()));

            window.statusBarColor = ContextCompat.getColor(context, color)
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun showSnackBar(context: Activity?, message: String) {
            if (context != null) {
                val contextView = context.findViewById<View>(android.R.id.content)
                val snackbar = Snackbar.make(contextView, message, Snackbar.LENGTH_SHORT)
                val snackBarView = snackbar.view
                snackBarView.background = context.getDrawable(R.drawable.snck_bg)
                val tv = snackBarView.findViewById<TextView>(R.id.snackbar_text)
                tv.textSize = 12f
                tv.setTextColor(ContextCompat.getColor(context, R.color.white))
                tv.gravity = Gravity.CENTER
                val params = snackBarView.layoutParams as ViewGroup.MarginLayoutParams
                params.setMargins(2, params.topMargin, 2, params.bottomMargin + 0)
                snackBarView.layoutParams = params
                snackbar.show()
            }

        }

        fun hideKeyboard(activity: Activity) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val f = activity.currentFocus
            if (null != f && null != f.windowToken && EditText::class.java.isAssignableFrom(f.javaClass))
                imm.hideSoftInputFromWindow(f.windowToken, 0)
            else
                activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }

        @SuppressLint("MissingPermission")
        fun isNetworkConnected(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected

            /* var haveConnectedWifi = false
            var haveConnectedMobile = false
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.allNetworkInfo
            for (ni in netInfo) {
                if (ni.typeName.equals("WIFI", ignoreCase = true)) if (ni.isConnected) haveConnectedWifi = true
                if (ni.typeName.equals("MOBILE", ignoreCase = true)) if (ni.isConnected) haveConnectedMobile = true
            }
            return haveConnectedWifi || haveConnectedMobile*/

        }

//        fun CustomeDialog(activity: Activity, alertDialog: AlertDialog) {
//            var alertDialog = alertDialog
//            try {
//
//                val builder = AlertDialog.Builder(activity, R.style.MyDialogTheme_1)
//
//                val frameView = FrameLayout(activity)
//                //frameView.setBackground(activity.getResources().getDrawable(R.drawable.dialog_bg));
//                builder.setView(frameView)
//
//                alertDialog = builder.create()
//                val inflater = alertDialog.layoutInflater
//                val dialogView = inflater.inflate(R.layout.custome_popup, frameView)
//
//                alertDialog.show()
//                /* alertDialog.getWindow().setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.dialog_bg));
//
//            CustomButton customButton = dialogView.findViewById(R.id.btLocation);
//            customButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    openGpsEnableSetting(activity);
//                }
//            });*/
//
//
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//        }

        fun locationPermission(activity: Activity?) {
            if (activity != null) {
                /* Snacky.builder()
                    .setActivity(activity)
                    .setActionText("Grant")
                    .setActionTextColor(activity.getResources().getColor(R.color.white))
                    .setBackgroundColor(activity.getResources().getColor(R.color.light_primary))
                    .setTextSize(12)
                    .setActionTextSize(12)
                    .setTextColor(activity.getResources().getColor(R.color.white))
                    .setTextTypefaceStyle(Typeface.BOLD)
                    .setActionClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                activity.requestPermissions(new String[]{ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                                        PERMISSION_LOCATION_REQUEST_CODE);
                            }
                        }
                    })
                    .setText("Permission Denied, You cannot access location data")
                    .setDuration(Snacky.LENGTH_INDEFINITE)
                    .build()
                    .show();*/
            }
        }

        fun checkPermission(activity: Activity): Boolean {
            val coarsePermissionCheck = ContextCompat.checkSelfPermission(
                activity,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            val finePermissionCheck = ContextCompat.checkSelfPermission(
                activity,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            if (coarsePermissionCheck && finePermissionCheck) {
                return true
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    activity.requestPermissions(
                        arrayOf(ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                        PERMISSION_LOCATION_REQUEST_CODE
                    )
                }
            }
            return false
        }

        fun getCurrentDateGmtFormat(selectedDate: String?): String? {
            var outputText = ""
            try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val date: Date = inputFormat.parse(selectedDate)
                // SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                inputFormat.timeZone = TimeZone.getTimeZone("UTC")
                outputText = inputFormat.format(date)
                ShowLog("Get Gmt Date1 =>", outputText)
            } catch (e: java.lang.Exception) {
            }
            return outputText
        }

        fun getCurrentTime(): String? {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            return inputFormat.format(Calendar.getInstance().time)
        }

//        fun showAlertForLogOut(activity: Activity) {
//            val dialog = Dialog(activity, R.style.dialogTheme)
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog.window!!.setGravity(Gravity.CENTER)
//            dialog.window!!.attributes.windowAnimations = R.anim.leftto
//            dialog.setCancelable(false)
//            dialog.setContentView(R.layout.dialog_logout)
//            val tvTitle: MaterialTextView = dialog.findViewById(R.id.tvTitle)
//            val tvMessage: MaterialTextView = dialog.findViewById(R.id.tvMessage)
//            val tvPositive: MaterialTextView = dialog.findViewById(R.id.tvPositive)
//            val tvNegative: MaterialTextView = dialog.findViewById(R.id.tvNegative)
//            tvMessage.text = activity.resources.getString(R.string.do_you_want_to_logout)
//            tvTitle.text = activity.resources.getString(R.string.are_you_sure)
//            tvPositive.text = activity.resources.getString(R.string.yes)
//            tvNegative.text = activity.resources.getString(R.string.no)
//
//            tvPositive.setOnClickListener {
//                SharedPreferenceManager.removeAllData()
//                val intent = Intent(activity, LoginActivity::class.java)
//                activity.startActivity(intent)
//                activity.overridePendingTransition(R.anim.rightto, R.anim.left)
//                activity.finishAffinity()
//
//                dialog.dismiss()
//            }
//            tvNegative.setOnClickListener {
//                dialog.dismiss()
//            }
//            dialog.show()
//        }

        fun Context.showCustomDialog(
            layout: Int,
            isCancelable: Boolean = true,
            listener: (view: View, alertDialog: androidx.appcompat.app.AlertDialog) -> Unit
        ) {
            val views = LayoutInflater.from(this).inflate(layout, null)
            val builder = androidx.appcompat.app.AlertDialog.Builder(this)
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


    }


}
/*   private void showSettingsDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
    builder.setTitle("Need Permissions");
    builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
    builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            openSettings();
        }
    });
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    });
    builder.show();

}*/

// navigating user to app settings
/*   private void openSettings() {
    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
    Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
    intent.setData(uri);
    startActivityForResult(intent, 101);
}*/



