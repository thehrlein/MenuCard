package com.tobiapplications.menu.utils.mvvm

import androidx.lifecycle.MediatorLiveData


/**
 * Created by tobias.hehrlein on 17.01.19.
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [Result.Error] to the result) is the subclasses's responsibility.
 */
abstract class MediatorUseCase<in I, O> {

    protected val result = MediatorLiveData<Result<O>>()

    // open so that mock instances can mock this method
    open fun observe(): MediatorLiveData<Result<O>> {
        return result
    }

    abstract fun execute(parameters: I)
}