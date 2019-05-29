package com.tobiapplications.menu.model

import com.tobiapplications.menu.utils.general.DisplayableItem

/**
 * Created by tobias.hehrlein on 2019-05-28.
 */
open class OrderItem(val name: String, val price: Double, var count: Int = 0) : DisplayableItem {

    fun increaseCount(count: Int) {
        this.count += count
    }
}