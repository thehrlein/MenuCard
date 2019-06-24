package com.tobiapplications.menu.ui.viewhandler.adapter

import com.tobiapplications.menu.ui.viewhandler.delegates.orderoverview.OrderBottomViewDelegate
import com.tobiapplications.menu.ui.viewhandler.delegates.orderoverview.OrderOverviewDelegate
import com.tobiapplications.menu.utils.general.BaseRecyclerViewAdapter

/**
 * Created by tobias.hehrlein on 2019-05-28.
 */
class OrderOverviewAdapter(onSendClicked: () -> Unit) : BaseRecyclerViewAdapter() {

    init {
        delegatesManager.addDelegate(OrderOverviewDelegate())
        delegatesManager.addDelegate(OrderBottomViewDelegate(onSendClicked))
    }
}