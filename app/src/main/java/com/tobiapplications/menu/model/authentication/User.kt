package com.tobiapplications.menu.model.authentication

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
data class User(val email: String, val admin: Boolean = false, var firebaseToken: MutableList<String> = ArrayList()) {

    // need empty constructor for deserializing from firestore
    constructor() : this("")
}