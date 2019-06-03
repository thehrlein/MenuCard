package com.tobiapplications.menu.utils.extensions

import com.tobiapplications.menu.utils.general.Constants


/**
 *  Created by tobiashehrlein on 2019-05-07
 */
fun String?.orEmpty() : String {
    return this ?: Constants.EMPTY_STRING
}

fun Int?.orDefault() : Int {
    return this ?: Constants.DEFAULT_INT
}

fun Double.formatEuro() : String {
    return String.format("%.2f €", this)
}

fun Boolean?.orFalse() : Boolean {
    return this ?: false
}