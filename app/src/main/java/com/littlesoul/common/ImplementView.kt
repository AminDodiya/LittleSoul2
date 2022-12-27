package com.littlesoul.common


interface ImplementView {

    fun email()

    fun emailValidation()

    fun passwordValidation()

    fun passwordMinValidation()

    fun success()


    interface SignUpImplementView {
        fun name()
        fun email()
        fun phone()
        fun phoneValidate()
        fun mobileMinValidation()
        fun emailValidation()
        fun passwordValidation()
        fun passwordMinValidation()
        fun passwordSpecialValidation()
        fun gender()
        fun success()

    }

    interface EditProfileImplementView {
        fun name()
        fun email()
        fun emailValidation()
        fun phone()
        fun phoneValidate()
        fun address()
        fun success()
    }

    interface AddressImplementView {
        fun name()
        fun phone()
        fun phoneValidate()
        fun city()
        fun address()
        fun postalcode()
        fun validPostalcode()

        fun success()
    }

}
