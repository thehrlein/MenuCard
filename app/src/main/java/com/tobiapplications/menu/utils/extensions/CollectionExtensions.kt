package com.tobiapplications.menu.utils.extensions

import com.tobiapplications.menu.utils.general.MenuUtils

/**
 *  Created by tobiashehrlein on 2019-06-23
 */
fun <E : Any, T : Collection<E>> T?.hasItems() : Boolean {
    return this != null && isNotEmpty()
}

fun Collection<Any?>?.getFlattenedList() : List<Any> {
    val flattend = mutableListOf<Any>()
    MenuUtils.flattenList(this, flattend)
    return flattend
}