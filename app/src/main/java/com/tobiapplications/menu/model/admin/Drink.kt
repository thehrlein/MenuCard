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
                 var count: Int = 0) : FireStoreItem(), OrderableItem, DisplayableItem {

    constructor() : this("","", 0.0)

    fun increaseCount(count: Int) {
        this.count += count
    }
//    override fun getCount(): Int {
//        return _count
//    }
//
//    override fun setCount(count: Int) {
//        _count = count
//    }
//
//    override fun getPrice(): Double {
//        return _price
//    }
//
//    override fun getName(): String {
//        return _name
//    }
//
//    /**
//     * Need for de-serialization
//     */
//    fun setName(name: String) {
//        _name = name
//    }
//
//    fun setPrice(price: Double){
//        _price = price
//    }
}