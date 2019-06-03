package com.tobiapplications.menu.ui.views.authenthication

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.tobiapplications.menu.R
import com.tobiapplications.menu.utils.enums.AuthenticationUiType
import com.tobiapplications.menu.utils.extensions.*
import kotlinx.android.synthetic.main.view_authentication_edittext.view.*


/**
 * Created by Tobias on 25.05.2017.
 */

class AuthEditText @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_authentication_edittext, this)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AuthEditText, defStyleAttr, 0)
        val type = typedArray.getInt(R.styleable.AuthEditText_type, 0)
        val hint = typedArray.getString(R.styleable.AuthEditText_hint)
        typedArray.recycle()

        loginTextInputLayout.hint = hint
        if (type == AuthenticationUiType.PASSWORD.value) {
            loginEditText.transformationMethod = PasswordTransformationMethod.getInstance()
        } else if (type == AuthenticationUiType.EMAIL.value) {
            loginEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }
    }

    fun addOnTextChangeListener(afterTextChanged: (String) -> Unit) {
        loginEditText.afterTextChanged { afterTextChanged(it) }
    }

    fun onFocusLost(onFocusLost: (String) -> Unit) {
        loginEditText.onFocusLost { onFocusLost(getText()) }
    }

    fun onEditorActionClicked(onClick: () -> Unit) {
        loginEditText.setOnEditorActionListener { _, _, _ -> consume { onClick() } }
    }

    fun getText(clearError: Boolean = false): String {
        if (clearError) {
            setErrorText(null)
        }
        return loginEditText.text.toString()
    }

    fun setText(text: String) {
        loginEditText.setText(text)
    }

    fun setErrorText(error: Int?) {
        if (error == null) {
            errorText.setGone()
        } else {
            errorText.show()
            errorText.text = getString(error)
        }
    }
}
