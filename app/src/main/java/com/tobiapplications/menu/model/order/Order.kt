package com.tobiapplications.menu.model.order

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
data class Order(var drinks: MutableList<Drink> = mutableListOf(), var shisha: MutableList<Shisha> = mutableListOf())