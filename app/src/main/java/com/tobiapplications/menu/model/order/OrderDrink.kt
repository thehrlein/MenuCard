package com.tobiapplications.menu.model.order

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
class OrderDrink(name: String, val size: String, price: Double, count: Int = 0) : OrderItem(name, price, count)