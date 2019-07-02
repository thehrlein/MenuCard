package com.tobiapplications.menu.utils.extensions

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Created by tobias.hehrlein on 2019-05-29.
 */
/** Convenience for callbacks/listeners whose return value indicates an event was consumed. */
inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}

/**
 * Calls the specified function [block] with `this` value as its argument and returns its result.
 */
inline fun <T, R> T?.letElse(block: (T) -> R, elseFunc: () -> R): R {
    return if (this != null) {
        block(this)
    } else {
        elseFunc()
    }
}