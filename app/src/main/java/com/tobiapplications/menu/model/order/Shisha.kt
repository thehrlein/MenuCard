package com.tobiapplications.menu.model.order

import com.tobiapplications.menu.model.admin.Tobacco
import com.tobiapplications.menu.utils.general.DisplayableItem


/**
 *  Created by tobiashehrlein on 2019-05-23
 */
data class Shisha(val name: String,
                  var tobaccos: List<Tobacco> = emptyList(),
                  val price: Double = 6.90,
                  var count: Int = 0) : OrderableItem, DisplayableItem {

    fun increaseCount(count: Int) {
        this.count += count
    }

    fun getQualifier() : String {
        return "${tobaccos.joinToString { it.getFullName() }}_$price"
    }
}