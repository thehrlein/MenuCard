package com.tobiapplications.menu.model.admin

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
data class GetManageDataModel(val collection: String, val clazz: Class<out FireStoreItem>)