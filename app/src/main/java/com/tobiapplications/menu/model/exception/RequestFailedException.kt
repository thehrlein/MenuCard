package com.tobiapplications.menu.model.exception

import com.tobiapplications.menu.utils.general.Constants

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
data class RequestFailedException(val reason: String? = Constants.EMPTY_STRING, val code: Int = 400) : Exception()