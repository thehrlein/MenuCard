package com.tobiapplications.menu.model

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
data class Order(var drinks: List<Drink> = emptyList(), var shisha: List<Shisha> = emptyList())