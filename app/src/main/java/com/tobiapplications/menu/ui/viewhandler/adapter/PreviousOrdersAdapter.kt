package com.tobiapplications.menu.ui.viewhandler.adapter

import com.tobiapplications.menu.ui.viewhandler.delegates.previousorders.PreviousOrderDelegate
import com.tobiapplications.menu.utils.general.BaseRecyclerViewAdapter

/**
 *  Created by tobiashehrlein on 2019-06-03
 */
class PreviousOrdersAdapter : BaseRecyclerViewAdapter() {

    init {
        delegatesManager.addDelegate(PreviousOrderDelegate())
    }


}