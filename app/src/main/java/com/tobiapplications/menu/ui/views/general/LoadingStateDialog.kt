package com.tobiapplications.menu.ui.views.general

import android.app.Dialog
import android.content.Context
import androidx.annotation.StyleRes
import android.view.LayoutInflater
import android.view.View
import com.tobiapplications.menu.R
import kotlinx.android.synthetic.main.view_loading_state_dialog.view.*

/**
 * Created by tobias.hehrlein on 03.01.19.
 */
class LoadingStateDialog @JvmOverloads constructor(context: Context, @StyleRes themeRes: Int = R.style.Loading_Dialog_Half_Width): Dialog(context, themeRes) {

    private val view = LayoutInflater.from(context).inflate(R.layout.view_loading_state_dialog, null, false)

    init {
        setCancelable(false)
        setContentView(view)
    }

    fun setLoadingState(message: String? = null) {
        view.progressBar.visibility = View.VISIBLE
        view.image.visibility = View.INVISIBLE
        view.text.visibility = View.INVISIBLE
        view.title.visibility = View.INVISIBLE
        message?.let {
            view.text.text = it
            view.text.visibility = View.VISIBLE
        }

    }

    fun setSuccessState(message: String) {
        view.title.visibility = View.INVISIBLE
        view.progressBar.visibility = View.GONE
        view.image.visibility = View.VISIBLE
        view.text.visibility = View.VISIBLE
        view.text.text = message
    }

    fun setFailureState(message: String) {
        view.title.visibility = View.VISIBLE
        view.progressBar.visibility = View.GONE
        view.image.visibility = View.INVISIBLE
        view.text.visibility = View.VISIBLE
        view.text.text = message
    }
}