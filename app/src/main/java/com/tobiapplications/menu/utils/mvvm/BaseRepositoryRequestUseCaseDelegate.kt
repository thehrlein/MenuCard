package com.tobiapplications.menu.utils.mvvm

import retrofit2.Response

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
interface BaseRepositoryRequestUseCaseDelegate<T> {

    fun onSuccess(response: Response<T>)
    fun onFailure(throwable: Throwable)
}