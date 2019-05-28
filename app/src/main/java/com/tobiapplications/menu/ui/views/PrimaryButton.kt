package com.tobiapplications.menu.ui.views

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.widget.FrameLayout
import com.tobiapplications.menu.R
import com.tobiapplications.menu.utils.extensions.getColor
import kotlinx.android.synthetic.main.view_primary_button.view.*

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
class PrimaryButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): FrameLayout(context, attrs, defStyleAttr) {

    private var layout : FrameLayout = inflate(context, R.layout.view_primary_button, this) as FrameLayout

    init {
        val styles = context.obtainStyledAttributes(attrs, R.styleable.PrimaryButton)
        val text = styles.getString(R.styleable.PrimaryButton_text)
        title.text = text
        setTextColor(styles.getColorStateList(R.styleable.PrimaryButton_textColor))
        isEnabled = styles.getBoolean(R.styleable.PrimaryButton_enabled, true)

        styles.recycle()
    }

    private fun setTextColor(colorStateList: ColorStateList?) {
        colorStateList?.let {
            title.setTextColor(it)
        }
    }

    fun setButtonSecondaryState() {
        layoutButtons.setBackgroundColor(getColor(R.color.colorLightGrey))
        title.setTextColor(getColor(R.color.colorBlack))
    }


    override fun setEnabled(enabled: Boolean) {
        if (enabled) {
            layout.layoutButtons.setBackgroundColor(getColor(R.color.colorPrimary))
        } else {
            layout.layoutButtons.setBackgroundColor(getColor(R.color.colorLightGrey))
        }

        super.setEnabled(enabled)
    }



}