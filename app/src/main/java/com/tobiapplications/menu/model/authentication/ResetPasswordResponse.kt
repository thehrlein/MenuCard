package com.tobiapplications.menu.model.authentication

import com.google.android.gms.tasks.Task

/**
 *  Created by tobiashehrlein on 2019-06-15
 */
data class ResetPasswordResponse(val email: String?, val result: Task<Void>)