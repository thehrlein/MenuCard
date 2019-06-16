package com.tobiapplications.menu.ui.views.authenthication

import android.content.Context
import android.content.SharedPreferences
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import com.tobiapplications.menu.R
import com.tobiapplications.menu.utils.extensions.*
import kotlinx.android.synthetic.main.view_authentication_email_auto_complete.view.*


/**
 * Created by Tobias on 19.05.2017.
 */

class EmailAutoCompleteTextView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_authentication_email_auto_complete, this)
    }

    fun addOnTextChangeListener(afterTextChanged: (String) -> Unit) {
        email.afterTextChanged { afterTextChanged(it) }
    }

    fun onFocusLost(onFocusLost: (String) -> Unit) {
        email.onFocusLost { onFocusLost(getText()) }
    }

    fun onEditorActionClicked(onClick: () -> Unit) {
        email.setOnEditorActionListener { _, _, _ -> consume { onClick() } }
    }

    fun initPrefilling(emailList: Array<String>) {
        email.setAdapter(ArrayAdapter(context,
            android.R.layout.simple_dropdown_item_1line,
            emailList))
    }

    fun getText(clearError: Boolean = false): String {
        if (clearError) {
            setErrorText(null)
        }
        return email.text.toString()
    }

    fun setText(text: String?) {
        email.setText(text)
    }

    fun setErrorText(error: Int?) {
        if (error == null) {
//            email.error = null
            email.background.clearColorFilter()
            errorText.setGone()
        } else {
//            email.error = getString(error)
            email.background.setColorFilter(getColor(R.color.colorRed), PorterDuff.Mode.SRC_IN)
            errorText.show()
            errorText.text = getString(error)
        }
    }
}
