package com.tobiapplications.menu.utils.general

import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.model.order.Shisha

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
object OrderUtils {

    private val order = Order()

    fun addDrinks(list: List<DisplayableItem>) {
        list.forEach { order ->
            if (order !is Drink) return
            this.order.drinks.firstOrNull { it.toString() == order.toString() }?.increaseCount(order.count) ?: this.order.drinks.add(order)
        }
    }

    fun addShishas(list: List<DisplayableItem>) {
        list.forEach { order ->
            if (order !is Shisha) return
            this.order.shisha.firstOrNull { it.name == order.name }?.increaseCount(order.count) ?: this.order.shisha.add(order)

        }
    }

    fun getOrder() : Order {
        return order
    }

    fun clearOrder() {
        order.drinks.clear()
        order.shisha.clear()
    }
}