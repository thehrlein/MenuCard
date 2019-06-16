package com.tobiapplications.menu.utils.general

import com.tobiapplications.menu.utils.persistence.SharedPreferencesManager
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class AuthenticationHelper @Inject constructor(private val sharedPreferencesManager: SharedPreferencesManager) {

    fun getEmailDictionary() : MutableSet<String> {
        return sharedPreferencesManager.getPrefernceStringSet(Constants.EMAIL_DICTIONARY)
    }

    fun addEmailToDictionary(email: String) {
        val prevList = getEmailDictionary()
        prevList.add(email)
        sharedPreferencesManager.setPreferenceStringSet(Constants.EMAIL_DICTIONARY, prevList.toHashSet())
    }
}