package com.tobiapplications.menu.model.order

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
data class Order(var orderDrinks: MutableList<OrderDrink> = mutableListOf(), var shisha: MutableList<Shisha> = mutableListOf())