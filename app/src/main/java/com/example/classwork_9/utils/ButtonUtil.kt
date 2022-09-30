package com.example.classwork_9.utils

import com.example.classwork_9.model.Indicator
import com.example.classwork_9.R
import com.example.classwork_9.model.ButtonActions
import com.example.classwork_9.model.ButtonTypes

object ButtonUtil {

    val CORRECT_PASSWORD = listOf<Indicator>(
        Indicator(id = 0, number = 0),
        Indicator(id = 1, number = 9),
        Indicator(id = 2, number = 3),
        Indicator(id = 3, number = 4)
    )

    val BUTTONS = listOf<ButtonTypes>(
        ButtonTypes.Numeric(1),
        ButtonTypes.Numeric(2),
        ButtonTypes.Numeric(3),
        ButtonTypes.Numeric(4),
        ButtonTypes.Numeric(5),
        ButtonTypes.Numeric(6),
        ButtonTypes.Numeric(7),
        ButtonTypes.Numeric(8),
        ButtonTypes.Numeric(9),
        ButtonTypes.Other(R.drawable.ic_fingerprint, ButtonActions.FINGERPRINT),
        ButtonTypes.Numeric(0),
        ButtonTypes.Other(R.drawable.ic_remove, ButtonActions.REMOVE),
    )

}