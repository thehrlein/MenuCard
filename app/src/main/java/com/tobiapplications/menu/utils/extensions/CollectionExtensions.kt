package com.tobiapplications.menu.utils.extensions

/**
 *  Created by tobiashehrlein on 2019-06-23
 */
fun <E : Any, T : Collection<E>> T?.hasItems() : Boolean {
    return this != null && isNotEmpty()
}