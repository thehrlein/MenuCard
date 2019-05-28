package com.tobiapplications.menu.model

import com.tobiapplications.menu.utils.general.DisplayableItem

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
data class Shisha(val name: String, val price: Double, var count: Int = 0) : DisplayableItem