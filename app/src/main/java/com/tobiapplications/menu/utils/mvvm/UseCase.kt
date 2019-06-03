package com.tobiapplications.menu.utils.mvvm

import androidx.lifecycle.MutableLiveData
import timber.log.Timber
import java.lang.Exception

/**
 * Created by tobias.hehrlein on 17.01.19.
 */
abstract class UseCase<in P, R> {

    private val taskScheduler = DefaultScheduler

    /** Executes the use case asynchronously and returns a [Result] in a new LiveData object.
     * @return an observable [LiveData] with a [Result].
     * @param parameters the input parameters to run the use case with
     */
    operator fun invoke(param : P) : MutableLiveData<Result<R>> {
        val liveCallback = MutableLiveData<Result<R>>()
        this(param, liveCallback)
        return liveCallback
    }

    /** Executes the use case asynchronously and places the [Result] in a MutableLiveData
     * @param parameters the input parameters to run the use case with
     * @param result the MutableLiveData where the result is posted to
     */
    operator fun invoke(param : P, liveData: MutableLiveData<Result<R>>) {
        try {
            taskScheduler.execute {
               try {
                   execute(param).let {
                       liveData.postValue(Result.Success(it))
                   }
               } catch (e : Exception) {
                    Timber.d(e)
                    liveData.postValue(Result.Error(e))
               }
            }
        } catch (e : Exception) {
            Timber.d(e)
            liveData.postValue(Result.Error(e))
        }
    }

    /**
     * Used to test use cases (executes the use case synchronously
     */
    fun executeNow(param: P): Result<R> {
        return try {
            Result.Success(execute(param))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    protected abstract fun execute(param: P): R

}