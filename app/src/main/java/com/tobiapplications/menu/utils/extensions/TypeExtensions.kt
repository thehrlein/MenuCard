package com.tobiapplications.menu.utils.extensions

import com.tobiapplications.menu.utils.general.Constants
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*


/**
 *  Created by tobiashehrlein on 2019-05-07
 */
fun String?.orEmpty() : String {
    return this ?: Constants.EMPTY_STRING
}

fun Int?.orDefault() : Int {
    return this ?: Constants.DEFAULT_INT
}

fun Long?.orDefault() : Long {
    return this ?: Constants.DEFAULT_LONG
}

fun Double.formatEuro() : String {
    return String.format("%.2f €", this)
}

fun Boolean?.orFalse() : Boolean {
    return this ?: false
}

/**
 * Apply's basic schedulers
 */
fun <T> Single<T>.applyScheduler() : Single<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.applyScheduler() : Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
