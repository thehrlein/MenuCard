package com.tobiapplications.menu.model.authentication

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
data class RegisterResponse(val task: Task<AuthResult>, val email: String)