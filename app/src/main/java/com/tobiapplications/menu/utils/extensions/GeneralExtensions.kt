package com.tobiapplications.menu.utils.extensions

/**
 * Created by tobias.hehrlein on 2019-05-29.
 */
/** Convenience for callbacks/listeners whose return value indicates an event was consumed. */
inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}