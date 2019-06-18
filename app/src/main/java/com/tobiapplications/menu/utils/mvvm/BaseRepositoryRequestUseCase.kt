package com.tobiapplications.menu.utils.mvvm

import com.tobiapplications.menu.model.exception.RequestFailedException
import com.tobiapplications.menu.utils.extensions.applyScheduler
import com.tobiapplications.menu.utils.repository.base.BaseRepository
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Response

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
abstract class BaseRepositoryRequestUseCase<I, O, R>(private val repo: BaseRepository<I, R>) : MediatorUseCase<I, O>(), BaseRepositoryRequestUseCaseDelegate<R> {

    private val compositeDisposable = CompositeDisposable()

    override fun execute(parameters: I) {
        compositeDisposable.add(repo.getData(parameters)
            .applyScheduler()
            .subscribe( { onSuccess(it) }, { throwable -> onFailure(throwable)}))
    }

    override fun onSuccess(response: Response<R>) {
        if (response.isSuccessful) {
            result.postValue(Result.Success(transformResponse(response.body())))
        } else {
            onFailure(RequestFailedException("${this.javaClass.simpleName} was not successful"))
        }
    }

    override fun onFailure(throwable: Throwable) {
        result.postValue(Result.Error(RequestFailedException(throwable.message)))
    }

    fun clear() {
        repo.clear()
    }

    /**
     * Method to transform response to whatever is needed
     */
    abstract fun transformResponse(input: R?) : O
}