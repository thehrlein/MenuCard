package com.tobiapplications.menu.model.authentication

/**
 * Created by tobias.hehrlein on 04.06.2019.
 */
data class LoginStateChanged(val hasLoggedIn: Boolean = false, val hasLoggedOut : Boolean = false)
