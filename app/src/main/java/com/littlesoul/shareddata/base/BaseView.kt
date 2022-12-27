package com.littlesoul.shareddata.base

interface BaseView {

    fun internalServer()

    fun onUnknownError(error: String?)

    fun onTimeout()

    fun onNetworkError()

    fun onConnectionError()

    //fun autoLogout()
}