package com.tobiapplications.menu.model.admin

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
data class AddToFireStoreModel(val collection: String, val value: FireStoreItem, val document: String? = null)