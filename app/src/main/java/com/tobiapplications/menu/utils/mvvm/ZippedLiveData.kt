package com.tobiapplications.menu.utils.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

/**
 * Created by tobias.hehrlein on 22.10.18.
 * Zips two LiveData sources together, emits if the result of the controlFunction is true and
 * returns the value of the returnFunction result
 */
open class ZippedLiveData<A, B, C>(a: LiveData<A>, b: LiveData<B>, val returnFunction: (A?, B?) -> C, val controlFunction: (A?, B?) -> Boolean) : MediatorLiveData<C>() {

    private var originalA : A? = null
    private var originalB : B? = null

    init {
        addSource(a) {
            originalA = it
            update()
        }
        addSource(b) {
            originalB = it
            update()
        }
    }

    private fun update() {
        val tempA = originalA
        val tempB = originalB

        if (controlFunction(tempA, tempB)) {
            value = returnFunction(tempA, tempB)
        }
    }
}