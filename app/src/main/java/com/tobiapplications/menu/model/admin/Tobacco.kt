package com.tobiapplications.menu.model.admin

import com.tobiapplications.menu.utils.general.DisplayableItem

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
data class Tobacco(val brand: String, val name: String) : FireStoreItem(), DisplayableItem {

    constructor() : this("", "")

    fun getFullName() : String {
        return "$brand $name"
    }
}