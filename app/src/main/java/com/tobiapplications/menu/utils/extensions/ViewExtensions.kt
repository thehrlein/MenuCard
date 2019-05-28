package com.tobiapplications.menu.utils.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.jakewharton.rxbinding2.view.RxView
import com.tobiapplications.menu.utils.general.MenuUtils
import io.reactivex.functions.Consumer
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Created by tobias.hehrlein on 07.02.19.
 */

private const val DEFAULT_CLICK_THROTTLE = 1000L
private val DEFAULT_ERROR_CONSUMER = Consumer<Throwable> { throwable -> Timber.d(throwable) }

fun View.onClick(func: () -> Unit) {
    onClick(func, DEFAULT_ERROR_CONSUMER)
}

fun View.onClick(func: () -> Unit, throttleMilliseconds: Long) {
    onClick(func, DEFAULT_ERROR_CONSUMER, throttleMilliseconds)
}

fun View.onClick(func: () -> Unit, error : Consumer<in Throwable>) {
    onClick(func, error, DEFAULT_CLICK_THROTTLE)
}

@SuppressLint("CheckResult")
fun View.onClick(func: () -> Unit, error : Consumer<in Throwable>, throttleMilliseconds : Long) {
    RxView.clicks(this)
            .throttleFirst(throttleMilliseconds, TimeUnit.MILLISECONDS)
            .subscribe(Consumer { func() }, error)
}

fun View.onLongClick(func: () -> Unit) {
    onLongClick(func, DEFAULT_ERROR_CONSUMER)
}

fun View.onLongClick(func: () -> Unit, error : Consumer<in Throwable>) {
    onLongClick(func, error, DEFAULT_CLICK_THROTTLE)
}

@SuppressLint("CheckResult")
fun View.onLongClick(func: () -> Unit, error : Consumer<in Throwable>, throttleMilliseconds : Long) {
    RxView.longClicks(this)
            .throttleFirst(throttleMilliseconds, TimeUnit.MILLISECONDS)
            .subscribe(Consumer { func() }, error)
}

fun View.getColor(res: Int) : Int {
    return ContextCompat.getColor(context, res)
}

fun View.getDimen(dimen: Int) : Float {
    return resources.getDimension(dimen)
}

fun View.getDrawable(drawable: Int) : Drawable? {
    return ContextCompat.getDrawable(context, drawable)
}

fun View.getString(stringRes: Int) : String? {
    return context.getString(stringRes)
}

fun View.getString(@StringRes resId: Int, vararg formatArgs: Any): String {
    return context.getString(resId, *formatArgs)
}

fun View.pxFromDp(dp: Float) : Int {
    return MenuUtils.pxFromDp(context, dp).toInt()
}

fun View.closeKeyboard() {
    post {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
}

fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.setGone() {
    visibility = View.GONE
}
