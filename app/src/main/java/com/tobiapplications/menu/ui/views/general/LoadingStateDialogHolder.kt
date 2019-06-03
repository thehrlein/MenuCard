package com.tobiapplications.menu.ui.views.general

import android.os.Handler

/**
 * Created by tobias.hehrlein on 08.01.19.
 */
interface LoadingStateDialogHolder {

    fun getLoadingDialog() : LoadingStateDialog

    fun setDialogLoadingState(message: String? = null) {
        getLoadingDialog().setLoadingState(message)
        getLoadingDialog().show()
    }

    fun setDialogSuccessState(message: String) {
        getLoadingDialog().setSuccessState(message)
    }

    fun setDialogFailureState(message: String) {
        getLoadingDialog().setFailureState(message)
    }

    fun dismissDialogDelayed(listener: (() -> Unit)? = null) {
        Handler().postDelayed({ dismissDialog(listener) }, 3000)
    }

    fun dismissDialog(listener: (() -> Unit)? = null) {
        getLoadingDialog().dismiss()
        listener?.invoke()
    }
}