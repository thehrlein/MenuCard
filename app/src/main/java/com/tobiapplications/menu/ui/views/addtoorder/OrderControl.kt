package com.tobiapplications.menu.ui.views.addtoorder

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.tobiapplications.menu.R
import com.tobiapplications.menu.utils.extensions.onClick
import com.tobiapplications.menu.utils.extensions.orDefault
import com.tobiapplications.menu.utils.general.TextChangeListener
import kotlinx.android.synthetic.main.view_order_control.view.*

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
class OrderControl @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    private var valueChangeListener : ((Int) -> Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_order_control, this)

        plus.onClick({ changeCount(1) }, 100)
        minus.onClick( { changeCount(-1) }, 100)

        value.addTextChangedListener(TextChangeListener { valueChangeListener?.invoke(it.toIntOrNull().orDefault()) })
    }

    private fun changeCount(add: Int) {
        var newCount = value.text.toString().toIntOrNull()?.orDefault()?.plus(add).orDefault()
        if (newCount < 0) {
            newCount = 0
        }
        value.text = newCount.toString()
    }

    fun setOnValueChangeListener(listener: (Int) -> Unit) {
        this.valueChangeListener = listener
    }
}