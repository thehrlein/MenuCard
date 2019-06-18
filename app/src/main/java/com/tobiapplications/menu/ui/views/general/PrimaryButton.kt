package com.tobiapplications.menu.ui.views.general

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.widget.FrameLayout
import com.tobiapplications.menu.R
import com.tobiapplications.menu.utils.extensions.getColor
import com.tobiapplications.menu.utils.extensions.getDrawable
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
        setState(styles.getBoolean(R.styleable.PrimaryButton_secondary, false))
        styles.recycle()
    }

    private fun setTextColor(colorStateList: ColorStateList?) {
        colorStateList?.let {
            title.setTextColor(it)
        }
    }

    private fun setState(secondary: Boolean) {
        if (secondary) {
            setButtonSecondaryState()
        } else {
            setButtonPrimaryState()
        }
    }

    private fun setButtonSecondaryState() {
        cardView.background = getDrawable(R.drawable.background_color_state_primary_button_secondary_state)
        title.setTextColor(getColor(R.color.colorBlack))
    }

    private fun setButtonPrimaryState() {
        cardView.background = getDrawable(R.drawable.background_color_state_primary_button_primary_state)
        title.setTextColor(getColor(R.color.colorWhite))
    }

    override fun setEnabled(enabled: Boolean) {
//        if (enabled) {
//            layout.layoutButtons.setBackgroundColor(getColor(R.color.colorPrimary))
//        } else {
//            layout.layoutButtons.setBackgroundColor(getColor(R.color.colorLightGrey))
//        }

        super.setEnabled(enabled)
    }
}