package com.tobiapplications.menu.model.order

import com.tobiapplications.menu.model.admin.FireStoreItem

/**
 * Created by tobias.hehrlein on 2019-06-21.
 */
data class UserOrder(val orders: List<Order>) : FireStoreItem() {

    constructor() : this(emptyList())
}