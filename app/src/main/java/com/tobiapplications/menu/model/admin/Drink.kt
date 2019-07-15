package com.tobiapplications.menu.model.admin

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.tobiapplications.menu.model.order.OrderableItem
import com.tobiapplications.menu.utils.general.DisplayableItem
import java.io.Serializable

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
data class Drink(var name: String,
                 var size: String,
                 var price: Double,
                 var count: Long = 0) : FireStoreItem(), OrderableItem, DisplayableItem {

    constructor() : this("","", 0.0)

    fun increaseCount(count: Long) {
        this.count += count
    }

    fun getQualifier() : String {
        return "${name}_${size}_$price"
    }
}