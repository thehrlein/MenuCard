package com.tobiapplications.timeline.utils.extensions

import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by tobias.hehrlein on 29.10.18.
 */

fun RecyclerView.ViewHolder.getString(@StringRes resourceId : Int) : String {
    return itemView.context.getString(resourceId)
}

fun RecyclerView.ViewHolder.getString(@StringRes resId: Int, vararg formatArgs: Any): String {
    return itemView.context.getString(resId, *formatArgs)
}

fun RecyclerView.ViewHolder.getColor(res: Int) : Int {
    return ContextCompat.getColor(itemView.context, res)
}