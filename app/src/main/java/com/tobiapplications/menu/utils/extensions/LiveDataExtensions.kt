package com.tobiapplications.menu.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

/**
 * Created by tobias.hehrlein on 27.01.2019.
 */


/** Uses `Transformations.map` on a LiveData */
fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(this, body)
}
//
//fun <T> LiveData<T>.nonNull(): NonNullMediatorLiveData<T> {
//    val mediator: NonNullMediatorLiveData<T> = NonNullMediatorLiveData()
//    mediator.addSource(this) { it?.let { mediator.value = it } }
//    return mediator
//}
//
//fun <T> NonNullMediatorLiveData<T>.observe(owner: LifecycleOwner, observer: (t: T) -> Unit) {
//    this.observe(owner, Observer { it?.let(observer) })
//}


