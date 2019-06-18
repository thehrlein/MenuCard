package com.tobiapplications.menu.ui.views.addtoorder

import android.content.Context
import android.util.AttributeSet
import com.github.clans.fab.FloatingActionButton
import com.tobiapplications.menu.R
import com.tobiapplications.menu.utils.extensions.getColor

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
class OrderButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FloatingActionButton(context, attrs, defStyleAttr) {

    init {
        buttonSize = SIZE_MINI
        setBackgroundColor(getColor(R.color.colorPrimaryLight))
    }
}