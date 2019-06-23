package com.tobiapplications.menu.utils.general

import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.model.order.Shisha
import com.tobiapplications.menu.utils.enums.OrderStatus

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
object OrderUtils {

    private var order = Order(status = OrderStatus.NEW)

    fun addDrinks(list: List<DisplayableItem>) {
        list.filterIsInstance<Drink>()
            .forEach { order ->
                this.order.drinks.firstOrNull { it.getQualifier() == order.getQualifier() }?.increaseCount(order.count) ?: this.order.drinks.add(order)
            }
    }

    fun addShishas(list: List<DisplayableItem>) {
        list.filterIsInstance<Shisha>()
            .forEach { order ->
                this.order.shisha.firstOrNull { it.getQualifier() == order.getQualifier() }?.increaseCount(order.count) ?: this.order.shisha.add(order)
            }
    }

    fun getOrder() : Order {
        return order
    }

    fun clearOrder() {
        order = Order(status = OrderStatus.NEW)
    }
}