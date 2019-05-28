package com.tobiapplications.menu.utils.general

import android.text.Editable
import android.text.TextWatcher

/**
 * Created by tobias.hehrlein on 14.11.18.
 */
class TextChangeListener(private val afterTextChangeListener: (String) -> Unit) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        // no implementation necessary
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        // no implementation necessary
    }

    override fun afterTextChanged(s: Editable) {
        afterTextChangeListener(s.toString())
    }
}