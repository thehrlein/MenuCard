package com.tobiapplications.menu.model

import com.tobiapplications.menu.utils.general.DisplayableItem

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
data class Drink(val name: String, val size: String, val price: Double, var count: Int = 0) : DisplayableItem