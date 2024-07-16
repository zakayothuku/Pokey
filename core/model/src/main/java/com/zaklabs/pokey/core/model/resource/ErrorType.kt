package com.zaklabs.pokey.core.model.resource

import kotlinx.serialization.Serializable

sealed class ErrorType {
    sealed class Network : ErrorType() {
        @Serializable data object Timeout : Network()

        @Serializable data object NoConnection : Network()

        @Serializable data object Forbidden : Network()

        @Serializable data object NotFound : Network()

        @Serializable data object Unauthorised : Network()

        @Serializable data object BadRequest : Network()

        @Serializable data object ServerError : Network()
    }

    sealed class Local : ErrorType() {
        @Serializable data object Unknown : Local()
    }
}
