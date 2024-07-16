package com.zaklabs.pokey.core.model.resource

/**
 * UI state
 *
 * @param T
 */
sealed class UIState<out T> {

    data object Loading : UIState<Nothing>()

    /**
     * Success
     *
     * @param T
     * @property data
     * @constructor Create empty Success
     */
    data class Success<T>(val data: T) : UIState<T>()

    /**
     * Error
     *
     * @property error
     * @constructor Create empty Error
     */
    data class Error(val error: ErrorType) : UIState<Nothing>()
}
