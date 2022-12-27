package com.littlesoul.ui.login

import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import com.littlesoul.R
import com.littlesoul.common.AppConstants
import com.littlesoul.common.AppConstants.DEVICE_ID
import com.littlesoul.common.AppConstants.IS_GUEST
import com.littlesoul.common.AppConstants.IS_LOGIN
import com.littlesoul.common.AppConstants.LOGGED_IN_USER_ID
import com.littlesoul.common.AppConstants.USER_NAME
import com.littlesoul.common.extentions.snack
import com.littlesoul.common.utils.Config
import com.littlesoul.common.utils.ReusedMethod
import com.littlesoul.common.utils.SharedPreferenceManager
import com.littlesoul.databinding.ActivityLoginBinding

import com.littlesoul.extentions.isVisible
import com.littlesoul.shareddata.base.BaseActivity

import com.littlesoul.ui.login.apiModel.LoginApiModel

import com.tajmeel._socialmedia.SmartUtils
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity(), View.OnClickListener {
    var dialog: Dialog? = null
    private lateinit var mBinding: ActivityLoginBinding
    private val mViewModel: LoginApiModel by viewModel()

    private fun initFCM() {
        FirebaseApp.initializeApp(this);
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task: Task<String?> ->
                if (!task.isSuccessful) {
                    //Could not get FirebaseMessagingToken
                    return@addOnCompleteListener
                }
                if (null != task.result) {
                    //Got FirebaseMessagingToken
                    val firebaseMessagingToken: String = task.result!!
                    SharedPreferenceManager.putString(DEVICE_ID, firebaseMessagingToken)
                    //Use firebaseMessagingToken further
                }
            }
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
    }

    private fun getKey() {
        val apiKey: String? = SharedPreferenceManager.getString(AppConstants.API_KEY_VALUE, "")
        if (apiKey == "") {
            if (ReusedMethod.isNetworkConnected(this@LoginActivity)) {
                mViewModel.getKey(true, true, this@LoginActivity)
            } else {
                displayMessage(resources.getString(R.string.internet_message_alert))
            }
        }
    }

    private fun initObserver() {
        mViewModel.getKeyRequest().observe(this, Observer { response ->
            response?.let { requestState ->
                requestState.apiResponse?.let {
                    it.data?.let { data ->
                        SharedPreferenceManager.putString(
                            AppConstants.API_KEY_VALUE,
                            data.apiKey.toString()
                        )
                    }
                }
                requestState.error?.let { errorObj ->
                    when (errorObj.errorState) {
                        Config.NETWORK_ERROR ->
                            displayMessage(getString(R.string.text_error_network))
                        Config.CUSTOM_ERROR ->
                            errorObj.customMessage
                                ?.let { displayMessage(it) }
                        else -> {

                        }
                    }
                }
            }
        })

        mViewModel.getLoginRequest().observe(this, Observer { response ->
            response?.let { requestState ->
                requestState.apiResponse?.let {

                    it.data?.let { data ->
                        showLoadingIndicator(false)
                        displayMessage("Successfully login")
                    }
                }
                requestState.error?.let { errorObj ->
                    when (errorObj.errorState) {
                        Config.NETWORK_ERROR -> {
                            showLoadingIndicator(false)
                            displayMessage(getString(R.string.text_error_network))
                        }
                        Config.CUSTOM_ERROR ->
                            errorObj.customMessage
                                ?.let {
                                    showLoadingIndicator(false)
                                    displayMessage(it)
                                }
                        else -> {

                        }
                    }
                }
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getResource(): Int {
        ReusedMethod.updateStatusBarColor(this, R.color.white, 0)
        return R.layout.activity_login
    }

    override fun onResume() {
        super.onResume()
        initFCM()
        getKey()
    }

    override fun initView() {
        mBinding = getBinding()
        // bindProgressButton(mBinding.btnSignin)
        //mBinding.btnSignin.attachTextChangeAnimator()

        initObserver()
        initObserverSkip()
    }


    override fun initProgress() {
        dialog = Dialog(this)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.getWindow()!!.setBackgroundDrawableResource(android.R.color.transparent);
        dialog!!.setContentView(R.layout.progress_dialog)
        dialog!!.setCancelable(false)
    }

    override fun initExpires(expired: Boolean) {

    }

    override fun handleListener() {
        mBinding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (mBinding.etEmail.length() < 1) {
                    mBinding.etEmail.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_email,
                        0
                    );

                } else {
                    if (!(SmartUtils.emailValidator(mBinding.etEmail.text.toString().trim()))) {
                        mBinding.etEmail.setBackgroundResource(R.drawable.et_backgroud_red)
                    } else {
                        mBinding.etEmail.setBackgroundResource(R.drawable.et_backgroud)
                    }
                    mBinding.etEmail.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_email_active,
                        0
                    );

                }
            }
        })
        mBinding.edPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (mBinding.edPassword.length() < 1) {
                    mBinding.edPassword.setBackgroundResource(R.drawable.et_backgroud)

                } else {
                    if (!SmartUtils.checkSpecialPasswordValidation(mBinding.edPassword.text.toString())) {
                        mBinding.edPassword.setBackgroundResource(R.drawable.et_backgroud_red)
                    } else {
                        mBinding.edPassword.setBackgroundResource(R.drawable.et_backgroud)
                    }

                }
            }
        })
        mBinding.tvSkip.setOnClickListener(this)
        mBinding.btnForgotPass.setOnClickListener(this)
        mBinding.btnSignup.setOnClickListener(this)
        mBinding.btnSignin.setOnClickListener(this)
        mBinding.chkPassword.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // show password
                mBinding.edPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                Log.i("checker", "true");
            } else {
                Log.i("checker", "false");
                // hide password
                mBinding.edPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }

        }
    }

    override fun showLoadingIndicator(isShow: Boolean) {
        dialog!!.isVisible(isShow, dialog)
    }

    override fun displayMessage(message: String) {
        mBinding.root.snack(message)
    }


    private fun initObserverSkip() {
        var type: String = ""

    }

    private fun getSkipApi() {

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tvSkip -> {
                getSkipApi()
            }

            R.id.btnSignin -> {
                ReusedMethod.hideKeyboard(this@LoginActivity)
                if (TextUtils.isEmpty(mBinding.etEmail.text.toString()) && TextUtils.isEmpty(
                        mBinding.edPassword.text.toString()
                    )
                ) {
                    displayMessage(getString(R.string.email_pas_error))
                    mBinding.etEmail.setBackgroundResource(R.drawable.et_backgroud_red)
                    mBinding.edPassword.setBackgroundResource(R.drawable.et_backgroud_red)
                } else if (TextUtils.isEmpty(mBinding.etEmail.text.toString())) {
                    displayMessage(getString(R.string.email_error))
                    mBinding.etEmail.setBackgroundResource(R.drawable.et_backgroud_red)
                } else if (!(SmartUtils.emailValidator(mBinding.etEmail.text.toString().trim()))) {
                    displayMessage(getString(R.string.email_valid_error))
                    mBinding.etEmail.setBackgroundResource(R.drawable.et_backgroud_red)
                } else if (TextUtils.isEmpty(mBinding.edPassword.text.toString())) {
                    displayMessage(getString(R.string.password_error))
                    mBinding.edPassword.setBackgroundResource(R.drawable.et_backgroud_red)
                } else if (!SmartUtils.checkSpecialPasswordValidation(mBinding.edPassword.text.toString())) {
                    displayMessage(getString(R.string.password_validation_error))
                    mBinding.edPassword.setBackgroundResource(R.drawable.et_backgroud_red)
                } else {
                    showLoadingIndicator(true)
                    loginApi(
                        mBinding.etEmail.text.toString().trim(),
                        mBinding.edPassword.text.toString().trim()
                    )
                }
            }
        }
    }

    private fun loginApi(etNameEmail: String, etPassword: String) {
        if (ReusedMethod.isNetworkConnected(this@LoginActivity)) {
            //  showLoadingIndicator(true)
            val deviceToken = SharedPreferenceManager.getString(DEVICE_ID, "")
            mViewModel.getLogin(
                true,
                true,
                this@LoginActivity,
                etNameEmail,
                etPassword,
                deviceToken
            )
        } else {
            displayMessage(resources.getString(R.string.internet_message_alert))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.leftto, R.anim.right)
        finish()
    }

}