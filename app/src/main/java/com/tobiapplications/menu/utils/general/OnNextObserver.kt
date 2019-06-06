package com.tobiapplications.menu.utils.general

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by tobias.hehrlein on 06.06.2019.
 */
interface OnNextObserver<T> : Observer<T>{

    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: T) {

    }

    override fun onError(e: Throwable) {

    }
}

