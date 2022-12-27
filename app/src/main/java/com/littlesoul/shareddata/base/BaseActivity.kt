package com.littlesoul.shareddata.base


import android.annotation.SuppressLint
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.littlesoul.R
import com.littlesoul.common.AppConstants
import com.littlesoul.common.utils.Config
import com.littlesoul.common.utils.LocaleUtils
import com.littlesoul.common.utils.ReusedMethod
import com.littlesoul.common.utils.SharedPreferenceManager
import com.littlesoul.shareddata.networkmanager.NetworkChangeCallback
import com.littlesoul.shareddata.networkmanager.NetworkChangeReceiver

import org.jetbrains.annotations.NotNull
import org.koin.android.viewmodel.ext.android.viewModel


abstract class BaseActivity : AppCompatActivity(), BaseView, NetworkChangeCallback {


    @LayoutRes
    abstract fun getResource(): Int

    private lateinit var binding: ViewDataBinding

    abstract fun initView()
    abstract fun initProgress()
    abstract fun initExpires(expired: Boolean)
    abstract fun handleListener()
    abstract fun showLoadingIndicator(isShow: Boolean)
    abstract fun displayMessage(message: String)

    var tempID = ""
    var isInternetConnected: Boolean = true
    //var apiCall: Boolean = true

    private var networkChangeReceiver: NetworkChangeReceiver? = null


    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(getResource(), savedInstanceState)
        networkChangeReceiver = NetworkChangeReceiver(this)
        initObserver()
//        val expired = SharedPreferenceManager.getBoolean(AppConstants.IS_EXPIRED, false)
//        if(!expired){
//            showExpiredDialog()
//        }
    }


    private fun initObserver() {

    }

    private fun setView(@LayoutRes layoutId: Int, savedInstanceState: Bundle?) {
        try {
            binding = DataBindingUtil.setContentView(this, layoutId)

            initProgress()
            initView()
            handleListener()
            val expired = SharedPreferenceManager.getBoolean(AppConstants.IS_EXPIRED, false)

            initExpires(expired)
        } catch (e: Exception) {
            Log.e(this.localClassName, e.printStackTrace().toString())
            // resToast(e.message!!)
        }
    }


    protected fun <T : ViewDataBinding> getBinding(): T {
        @Suppress("UNCHECKED_CAST")
        return binding as T
    }

    private lateinit var mToolbar: Toolbar
    override fun onDestroy() {
        super.onDestroy()
        if (networkChangeReceiver != null) {
            unregisterReceiver(networkChangeReceiver)
        }
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)

        registerReceiver(networkChangeReceiver, intentFilter)
    }

    @SuppressLint("RestrictedApi")
    protected fun setToolbar(
        @NotNull toolbar: Toolbar, @NotNull title: String, isBackEnabled: Boolean = false,
        backgroundColor: Int = R.color.colorPrimary
    ) {
        this.mToolbar = toolbar
        super.setSupportActionBar(toolbar)
        toolbar.setBackgroundColor(ContextCompat.getColor(this, backgroundColor))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.title = title

        toolbar.title = title

        if (isBackEnabled) {
            supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
            //if (backgroundColor == R.color.white)
            // toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
            toolbar.setNavigationOnClickListener { onBackPressed() }
        }
    }

    fun setTitle(@NotNull title: String) {
        this.mToolbar.title = title
    }


    fun setNavigationIcon(navigationIconResId: Int) {
        if (::mToolbar.isInitialized) {
            mToolbar.setNavigationIcon(navigationIconResId)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item!!)
            }
        }
    }


    fun changeFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        layout: Int,
        addToBackStack: Boolean = false
    ) {
        fragmentManager.beginTransaction().replace(layout, fragment, fragment::class.java.name)
            .commit()
    }

    fun checkFragmentVisible(fragmentManager: FragmentManager, fragmentName: String): Boolean {
        return fragmentManager.findFragmentByTag(fragmentName)?.isVisible ?: false
    }

    override fun onUnknownError(error: String?) {
        error?.let {
            Log.d(this.localClassName, "Base Activity Unknown error $error")
            displayMessage(error)
        }
    }

    override fun internalServer() {
        Log.d(this.localClassName, "Base Activity API Internal server")
        displayMessage(getString(R.string.text_error_internal_server))
    }

    override fun onTimeout() {
        Log.d(this.localClassName, "Base Activity API Timeout")
        displayMessage(getString(R.string.text_error_timeout))
    }

    override fun onNetworkError() {
        Log.d(this.localClassName, "Base Activity network error")
        displayMessage(getString(R.string.text_error_network))
    }

    override fun onConnectionError() {
        Log.d(this.localClassName, "Base Activity internet issue")
        displayMessage(getString(R.string.text_error_connection))
    }

    override fun attachBaseContext(newBase: Context?) {
        //super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
        super.attachBaseContext(LocaleUtils.setLocale(newBase!!, "en"))
    }

    override fun onNetworkChanged(status: Boolean) {
        try {
            if (status) {
                Log.v("@@internet", "true")
                //getTasks()
            } else {
                Log.v("@@internet", "false")
            }
        } catch (e: Exception) {
            Log.v("@@Uplaod", "Database uplaod issue")
        }
    }

    private fun updateTimeAPI(
        jsonArrayDuration: JsonArray,
        categoryID: String
    ) {

    }


/* override fun autoLogout() {
     mUserHolder?.clearData()
     val intent = Intent(this, SignInActivity::class.java)
     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
             or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
     startActivity(intent)
 }*/
}