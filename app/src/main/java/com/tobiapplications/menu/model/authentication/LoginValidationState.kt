package com.tobiapplications.menu.model.authentication

/**
 *  Created by tobiashehrlein on 2019-06-03
 */
data class LoginValidationState(val emailError: Int? = null,
                                val passwordError: Int? = null)