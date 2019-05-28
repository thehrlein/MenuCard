package com.tobiapplications.menu.utils.general

import com.tobiapplications.menu.model.Drink
import com.tobiapplications.menu.model.Order
import com.tobiapplications.menu.model.Shisha
import com.tobiapplications.menu.utils.enums.OrderType

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
object OrderUtils {

    private val order = Order()

    fun add(type: OrderType?, list: List<DisplayableItem>?) {
        if (type == null || list.isNullOrEmpty()) {
            return
        }

        when (type) {
            OrderType.DRINKS -> order.drinks = order.drinks.union(list.map { it as? Drink}).filterNotNull().toList()
            OrderType.SHISHA -> order.shisha = order.shisha.union(list.map { it as? Shisha}).filterNotNull().toList()
        }
    }

    fun getOrder() : Order {
        return order
    }

    fun clearOrder() {
        order.drinks = emptyList()
        order.shisha = emptyList()
    }

}