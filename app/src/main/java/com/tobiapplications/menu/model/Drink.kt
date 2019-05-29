package com.tobiapplications.menu.model

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
class Drink(name: String, val size: String, price: Double, count: Int = 0) : OrderItem(name, price, count)