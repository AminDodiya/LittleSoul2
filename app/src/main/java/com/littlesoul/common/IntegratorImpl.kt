package com.littlesoul.common

import android.text.TextUtils
import com.tajmeel._socialmedia.SmartUtils

object IntegratorImpl {
    fun isValidSignUP(
        name: String,
        email: String,
        password: String,
        mobileNumber: String,
        gender: String,
        signUpImplementView: ImplementView.SignUpImplementView
    ) {

        if (TextUtils.isEmpty(name)) {
            signUpImplementView.name()
        } else if (TextUtils.isEmpty(email)) {
            signUpImplementView.email()
        } else if (!SmartUtils.emailValidator(email)) {
            signUpImplementView.emailValidation()
        } else if (TextUtils.isEmpty(password)) {
            signUpImplementView.passwordValidation()
        } else if (password.length < 8) {
            signUpImplementView.passwordMinValidation()
        } else if (!SmartUtils.checkSpecialPasswordValidation(password)) {
            signUpImplementView.passwordSpecialValidation()
        } else if (mobileNumber.length < 8) {
            signUpImplementView.mobileMinValidation()
        } else if (TextUtils.isEmpty(mobileNumber)) {
            signUpImplementView.phone()
        } else if (mobileNumber.length > 15) {
            signUpImplementView.phoneValidate()
        } else if (TextUtils.isEmpty(gender)) {
            signUpImplementView.gender()
        } else {
            signUpImplementView.success()
        }
    }

    fun isValidLogin(
        email: String,
        password: String,
        signUpImplementView: ImplementView
    ) {

        if (TextUtils.isEmpty(email)) {
            signUpImplementView.email()
        } else if (!SmartUtils.emailValidator(email)) {
            signUpImplementView.emailValidation()
        } else if (TextUtils.isEmpty(password)) {
            signUpImplementView.passwordValidation()
        } else if (password.length < 6) {
            signUpImplementView.passwordMinValidation()
        } else {
            signUpImplementView.success()
        }
    }

    fun isValidEditProfile(
        name: String,
        email: String,
        mobileNumber: String,
        address: String,
        editProfileImplementView: ImplementView.EditProfileImplementView
    ) {
        if (TextUtils.isEmpty(name)) {
            editProfileImplementView.name()
        } else if (TextUtils.isEmpty(email)) {
            editProfileImplementView.email()
            editProfileImplementView.emailValidation()
        } else if (TextUtils.isEmpty(address)) {
            editProfileImplementView.address()
        } else if (TextUtils.isEmpty(mobileNumber)) {
            editProfileImplementView.phone()
        } else if (mobileNumber.length < 8) {
            editProfileImplementView.phoneValidate()
        } else {
            editProfileImplementView.success()
        }
    }

    fun isValidAddress(
        name: String,
        phone: String,
        city: String,
        address1: String,
        postalCode: String,
        AddressImplementView: ImplementView.AddressImplementView
    ) {
        if (TextUtils.isEmpty(name)) {
            AddressImplementView.name()
        } else if (TextUtils.isEmpty(phone)) {
            AddressImplementView.phone()
        } else if (phone.length < 8) {
            AddressImplementView.phoneValidate()
        } else if (phone.length > 15) {
            AddressImplementView.phoneValidate()
        } else if (TextUtils.isEmpty(city)) {
            AddressImplementView.city()
        } else if (TextUtils.isEmpty(address1)) {
            AddressImplementView.address()
        } else if (TextUtils.isEmpty(postalCode)) {
            AddressImplementView.postalcode()
        } else if (postalCode.length < 6) {
            AddressImplementView.validPostalcode()
        } else if (postalCode.length > 10) {
            AddressImplementView.validPostalcode()
        } else {
            AddressImplementView.success()
        }
    }
}
