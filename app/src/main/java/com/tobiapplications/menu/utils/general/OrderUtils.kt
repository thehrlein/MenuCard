package com.tobiapplications.menu.utils.general

import com.tobiapplications.menu.model.order.OrderDrink
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.model.order.Shisha
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
            OrderType.DRINKS -> addDrinks(list)
            OrderType.SHISHA -> addShishas(list)
        }
    }

    private fun addDrinks(list: List<DisplayableItem>) {
        list.forEach { order ->
            if (order !is OrderDrink) return
            this.order.orderDrinks.firstOrNull { it.name == order.name }?.increaseCount(order.count) ?: this.order.orderDrinks.add(order)
        }
    }

    private fun addShishas(list: List<DisplayableItem>) {
        list.forEach { order ->
            if (order !is Shisha) return
            this.order.shisha.firstOrNull { it.name == order.name }?.increaseCount(order.count) ?: this.order.shisha.add(order)

        }
    }

    fun getOrder() : Order {
        return order
    }

    fun clearOrder() {
        order.orderDrinks.clear()
        order.shisha.clear()
    }
}