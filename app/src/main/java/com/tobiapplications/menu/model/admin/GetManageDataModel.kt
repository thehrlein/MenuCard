package com.tobiapplications.menu.model.admin

import com.tobiapplications.menu.utils.general.DisplayableItem

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
data class GetManageDataModel(val collection: String, val clazz: Class<out BuyableItem>)