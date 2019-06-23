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

    fun flattenList(nestedList: Collection<Any?>?, flatList: MutableList<Any>) {
        nestedList
            ?.filterNotNull()
            ?.forEach { e ->
            when (e) {
                !is List<Any?> -> flatList.add(e)
                else -> flattenList(e, flatList)
            }
        }
    }
}