package com.tobiapplications.menu.ui.viewhandler.adapter

import com.tobiapplications.menu.ui.viewhandler.delegates.orderoverview.OrderOverviewDelegate
import com.tobiapplications.menu.utils.general.BaseRecyclerViewAdapter

/**
 * Created by tobias.hehrlein on 2019-05-28.
 */
class OrderOverviewAdapter : BaseRecyclerViewAdapter() {

    init {
        delegatesManager.addDelegate(OrderOverviewDelegate())
    }
}