package com.tobiapplications.menu.ui.viewhandler

import com.tobiapplications.menu.model.order.Shisha
import com.tobiapplications.menu.ui.viewhandler.delegates.order.ShishaDelegate
import com.tobiapplications.menu.utils.general.BaseRecyclerViewAdapter

/**
 *  Created by tobiashehrlein on 2019-06-17
 */
class AddShishaAdapter : BaseRecyclerViewAdapter() {

    init {
        delegatesManager.addDelegate(ShishaDelegate())
    }

    fun addItem(shisha: Shisha) {
        itemList.add(shisha)
        notifyItemInserted(itemList.indexOf(shisha))
    }
}