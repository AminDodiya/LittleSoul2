package com.littlesoul.shareddata.networkmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkChangeReceiver extends BroadcastReceiver {

    private NetworkChangeCallback callback;
    private static boolean firstConnect = true;

    public NetworkChangeReceiver(NetworkChangeCallback callback) {

        this.callback = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean status = isNetworkAvailable(context);
        showLog("" + status);
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null) {
            if (firstConnect) {
                if (callback != null) {
                    callback.onNetworkChanged(status);

                }
                firstConnect = false;
            }
        } else {
            firstConnect = true;
        }

    }

    private boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
            return (activeNetworkInfo != null && activeNetworkInfo.isConnected());
        } catch (NullPointerException e) {
            showLog(e.getLocalizedMessage());
            return false;
        }
    }

    private void showLog(String message) {
        Log.e("NetworkChangeReceiver", "" + message);
    }
}