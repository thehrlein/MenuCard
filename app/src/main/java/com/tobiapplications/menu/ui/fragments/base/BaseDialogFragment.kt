package com.tobiapplications.menu.ui.fragments.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.tobiapplications.menu.R
import dagger.android.support.DaggerDialogFragment
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 2019-06-18.
 */
abstract class BaseDialogFragment : DaggerDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var dismissListener: ((Boolean) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.BaseDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    protected fun adjustAndResizeWindow() {
        if (dialog?.window != null) {
            dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
        context
    }

    fun setOnDialogDismissListener(onDialogDismissActionDoneListener: (Boolean) -> Unit) {
        this.dismissListener = onDialogDismissActionDoneListener
    }

    abstract fun init()
    abstract fun getLayout(): Int
}