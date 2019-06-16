package com.tobiapplications.menu.ui.viewhandler.delegates

import com.tobiapplications.menu.ui.viewhandler.delegates.admin.AdminDrinkDelegate
import com.tobiapplications.menu.utils.general.BaseRecyclerViewAdapter

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class ManageDrinksAdapter : BaseRecyclerViewAdapter() {

    init {
        delegatesManager.addDelegate(AdminDrinkDelegate())
    }
}