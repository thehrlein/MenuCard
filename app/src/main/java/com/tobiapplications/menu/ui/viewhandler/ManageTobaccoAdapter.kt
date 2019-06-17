package com.tobiapplications.menu.ui.viewhandler

import com.tobiapplications.menu.ui.viewhandler.delegates.admin.AdminTobaccoDelegate
import com.tobiapplications.menu.utils.general.BaseRecyclerViewAdapter

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
class ManageTobaccoAdapter : BaseRecyclerViewAdapter() {

    init {
        delegatesManager.addDelegate(AdminTobaccoDelegate())
    }
}