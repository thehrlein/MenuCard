package com.tobiapplications.menu.model.order

import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.admin.Tobacco
import com.tobiapplications.menu.utils.extensions.getString
import com.tobiapplications.menu.utils.general.DisplayableItem


/**
 *  Created by tobiashehrlein on 2019-05-23
 */
data class Shisha(val name: String,
                  var tobaccos: List<Tobacco> = emptyList(),
                  val price: Double = 6.90,
                  var count: Long = 0) : OrderableItem, DisplayableItem {

    constructor() : this("", emptyList(), 0.0, 0)

    fun increaseCount(count: Long) {
        this.count += count
    }

    fun getQualifier() : String {
        return "${tobaccos.joinToString { it.getFullName() }}_$price"
    }

    fun getTobaccoList(placeHolder: String? = ", ") : String {
        var text = tobaccos.first().getFullName()
        tobaccos.filterIndexed { index, tobacco -> index > 0 }.forEach {
            text += "$placeHolder${it.getFullName()}"

        }

        return text
    }
}