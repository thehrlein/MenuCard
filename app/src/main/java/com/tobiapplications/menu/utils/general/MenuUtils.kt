package com.tobiapplications.menu.utils.general

import android.content.Context

/**
 *  Created by tobiashehrlein on 2019-05-22
 */
object MenuUtils {

    fun pxFromDp(context: Context, dp: Float): Float {
        val density = context.resources.displayMetrics.density
        return dp * density
    }
}