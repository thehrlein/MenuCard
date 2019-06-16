package com.tobiapplications.menu.model.authentication

import java.io.Serializable

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
data class User(val email: String, val admin: Boolean = false) {

    // need empty constructor for deserializing from firestore
    constructor() : this("")
}