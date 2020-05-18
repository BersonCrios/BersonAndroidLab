package br.com.bersoncrios.myandroidlab.core.service

sealed class ServiceResult<out T : Any> {
    class Success<out T : Any>(val data: T) : ServiceResult<T>()
    class Error(val exception: Throwable) : ServiceResult<Nothing>()
}