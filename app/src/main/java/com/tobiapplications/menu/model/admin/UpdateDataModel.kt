package com.tobiapplications.menu.model.admin

/**
 *  Created by tobiashehrlein on 2019-06-25
 */
data class UpdateDataModel(val collection: String,
                           val document: String,
                           val field: String,
                           val newValue: Any,
                           val timeStamp: Long)