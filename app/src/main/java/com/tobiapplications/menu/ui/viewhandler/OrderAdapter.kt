package com.tobiapplications.menu.ui.viewhandler

import com.tobiapplications.menu.ui.viewhandler.delegates.DrinkDelegate
import com.tobiapplications.menu.ui.viewhandler.delegates.ShishaDelegate
import com.tobiapplications.menu.utils.general.BaseRecyclerViewAdapter

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
class OrderAdapter : BaseRecyclerViewAdapter() {

    init {
        delegatesManager.addDelegate(DrinkDelegate())
        delegatesManager.addDelegate(ShishaDelegate())
    }
}