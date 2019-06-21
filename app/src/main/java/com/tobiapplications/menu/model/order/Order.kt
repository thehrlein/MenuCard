package com.tobiapplications.menu.model.order

import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.model.admin.FireStoreItem
import com.tobiapplications.menu.utils.enums.OrderStatus
import com.tobiapplications.menu.utils.general.DisplayableItem
import java.util.*

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
data class Order(var drinks: MutableList<Drink> = mutableListOf(),
                 var shisha: MutableList<Shisha> = mutableListOf(),
                 val timeStamp: Long = Date().time,
                 var status: OrderStatus) : FireStoreItem(), DisplayableItem {

    constructor() : this(mutableListOf<Drink>(), mutableListOf<Shisha>(), 0, OrderStatus.NEW)
}