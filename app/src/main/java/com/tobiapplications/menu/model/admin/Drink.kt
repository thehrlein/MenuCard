package com.tobiapplications.menu.model.admin

import com.tobiapplications.menu.utils.general.DisplayableItem

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
data class Drink(val name: String, val size: String, val price: Double, var id: String? = null) : DisplayableItem {

    constructor() : this("","", 0.0, null)
}