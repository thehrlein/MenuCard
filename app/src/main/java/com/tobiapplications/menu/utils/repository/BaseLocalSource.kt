package com.tobiapplications.menu.utils.repository


/**
 * Created by tobias.hehrlein on 10.01.19.
 */
open class BaseLocalSource<I, T> : LocalSourceDelegate<I, T> {

    private var t : T? = null

    override fun get(): T? {
        return t
    }

    override fun set(response: T?) {
        t = response
    }
}