package com.tobiapplications.menu.model.admin

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
data class ManageDataModel(val collection: String, val document: String? = null, val clazz: Class<out FireStoreItem>? = null)