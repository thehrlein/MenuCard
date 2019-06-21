package com.tobiapplications.menu.model.order

/**
 * Created by tobias.hehrlein on 2019-06-21.
 */
data class UserOrder(val list: List<Order>) {

    constructor() : this(emptyList())
}