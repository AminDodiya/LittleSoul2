package com.littlesoul.shareddata.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.littlesoul.R
import org.jetbrains.annotations.NotNull


abstract class BaseFragment : Fragment(), BaseView {

    /**
     * to get Fragment resource file
     */
    @LayoutRes
    abstract fun getInflateResource(): Int

    /**
     * to set fragment option menu
     */
    protected open fun hasOptionMenu(): Boolean = false

    /**
     * to display error message
     */
    abstract fun displayMessage(message: String)

    /**
     * to initialize variables
     */
    abstract fun initView()

    /**
     * to initialize progress dialog
     */
    abstract fun initProgress()

    /**
     * to call API or bind com.example.com.tajmeel.ui.signup.adapter
     */
    abstract fun postInit()

    /**
     * to define all listener
     */
    abstract fun handleListener()

//    private val dialog by lazy {
//        Dialog(requireContext())
//    }
//    lateinit var mDisposable: CompositeDisposable

    var isInternetConnected: Boolean = true
    abstract fun showLoadingIndicator(isShow: Boolean)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  mDisposable = CompositeDisposable()
        initConnectivity()
    }

    private lateinit var binding: ViewDataBinding

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getInflateResource(), container, false)

        setHasOptionsMenu(hasOptionMenu())
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initProgress()

        handleListener()
        postInit()
    }

    fun setTitle(@NotNull title: String) {
        (context as BaseActivity).setTitle(title)
    }

    @Suppress("UNCHECKED_CAST")
    @NonNull
    protected fun <T : ViewDataBinding> getBinding(): T {
        return binding as T
    }


    private fun initConnectivity() {
//        val settings = InternetObservingSettings.builder()
//            .host("www.google.com")
//            .strategy(SocketInternetObservingStrategy())
//            .interval(3000)
//            .build()
//
//        ReactiveNetwork
//            .observeInternetConnectivity(settings)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { isConnectedToHost ->
//                isInternetConnected = isConnectedToHost
//            }.addTo(mDisposable)
    }

    override fun onUnknownError(error: String?) {
        error?.let {
            // Timber.d("Base Activity $error")
            displayMessage(error)
        }
    }

    /**
     * @param isCancelable
     */
    fun displayCustomProgressDialog(isCancelable: Boolean) {
//        if (dialog.isShowing.not()) {
//
//            dialog.setContentView(R.layout.item_progressbar)
//
//            dialog.findViewById<FrameLayout>(R.id.progressBar).visibility = View.VISIBLE
//            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//
//            dialog.setCancelable(isCancelable)
//            dialog.show()
//        }

    }

    fun closeCustomProgressDialog() {
//        if (dialog.isShowing) {
//            dialog.dismiss()
//        }
    }

    override fun internalServer() {
        //Timber.d("Base Activity API Internal server")
        displayMessage(getString(R.string.text_error_internal_server))
    }

    override fun onTimeout() {
        //Timber.d("Base Activity API Timeout")
        displayMessage(getString(R.string.text_error_timeout))
    }

    override fun onNetworkError() {
        //Timber.d("Base Activity network error")
        displayMessage(getString(R.string.text_error_network))
    }

    override fun onConnectionError() {
        // Timber.d("Base Activity internet issue")
        displayMessage(getString(R.string.text_error_connection))
    }

}




