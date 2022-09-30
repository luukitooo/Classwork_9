package com.example.classwork_9.model

import androidx.annotation.DrawableRes

sealed interface ButtonTypes {
    data class Numeric(val number: Int): ButtonTypes
    data class Other(@DrawableRes val icon: Int, val action: ButtonActions): ButtonTypes
}

enum class ButtonActions {
    FINGERPRINT,
    REMOVE
}