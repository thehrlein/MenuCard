package com.tobiapplications.menu.ui.viewhandler.adapter

import com.tobiapplications.menu.model.order.Shisha
import com.tobiapplications.menu.ui.viewhandler.delegates.order.ShishaDelegate
import com.tobiapplications.menu.utils.general.BaseRecyclerViewAdapter

/**
 *  Created by tobiashehrlein on 2019-06-17
 */
class AddShishaAdapter(onSelectTobaccoClicked: (Int, Shisha) -> Unit) : BaseRecyclerViewAdapter() {

    init {
        delegatesManager.addDelegate(ShishaDelegate(onSelectTobaccoClicked))
    }

    fun addItem(shisha: Shisha) {
        itemList.add(shisha)
        notifyItemInserted(itemList.indexOf(shisha))
    }
}