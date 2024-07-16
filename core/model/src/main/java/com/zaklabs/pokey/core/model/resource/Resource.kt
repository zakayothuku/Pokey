package com.zaklabs.pokey.core.model.resource

/**
 * Class to handle api requests state
 */
sealed class Resource<T> {
    abstract val data: T?

    data class Loading<T>(override val data: T? = null) : Resource<T>()

    data class Success<T>(override val data: T?) : Resource<T>()

    data class Error<T>(override val data: T? = null, val error: ErrorType?) : Resource<T>()
}
