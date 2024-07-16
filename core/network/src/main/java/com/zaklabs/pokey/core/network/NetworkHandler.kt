package com.zaklabs.pokey.core.network

import com.zaklabs.pokey.core.model.mapper.RepositoryMapper
import com.zaklabs.pokey.core.model.resource.ErrorType
import com.zaklabs.pokey.core.model.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Network handler
 *
 * @param DBEntity
 * @param DTO
 * @param Data
 * @param query
 * @param fetch
 * @param cacheResponse
 * @param shouldFetch
 * @param repositoryMapper
 * @receiver
 * @receiver
 * @receiver
 * @receiver
 */
inline fun <DBEntity, DTO, Data> networkHandler(
    crossinline query: suspend () -> Flow<DBEntity?> = { flow {} },
    crossinline fetch: suspend () -> Response<DTO>,
    crossinline cacheResponse: suspend (DBEntity) -> Unit = {},
    crossinline shouldFetch: (DBEntity?) -> Boolean = { true },
    repositoryMapper: RepositoryMapper<DTO, DBEntity, Data?>,
) = flow {

    emit(Resource.Loading())

    val flow: Flow<Resource<Data>> = try {
        // get current (cached/stale) data from the database
        val data = query().first()

        if (shouldFetch(data)) {
            // attempt to fetch fresh data from the network
            val response = fetch()

            when {
                response.isSuccessful -> {
                    // save the fresh data to the database
                    response.body()?.let(repositoryMapper::dtoToDBEntity)?.also { dbEntity ->
                        cacheResponse(dbEntity)
                    }

                    // run the db query to emit the fresh data
                    query().map { Resource.Success(data = it?.let(repositoryMapper::dbEntityToData)) }
                }

                else -> {
                    query().map {
                        Resource.Error(
                            data = it?.let(repositoryMapper::dbEntityToData),
                            error = handleApiError(response.code()),
                        )
                    }
                }
            }
        } else {
            // return the cached data we have in the database without fetching from the network
            query().map { Resource.Success(data = it?.let(repositoryMapper::dbEntityToData)) } // flow to emit
        }
    } catch (e: Exception) {
        query().map {
            Resource.Error(
                data = it?.let(repositoryMapper::dbEntityToData),
                error = handleApiExceptions(e),
            )
        }
    }

    emitAll(flow)

}.flowOn(Dispatchers.IO)

fun handleApiError(errorCode: Int) =
    when (errorCode) {
        ErrorStatusCodes.BAD_REQUEST -> ErrorType.Network.BadRequest
        ErrorStatusCodes.UNAUTHORIZED -> ErrorType.Network.Unauthorised
        ErrorStatusCodes.FORBIDDEN -> ErrorType.Network.Forbidden
        ErrorStatusCodes.NOT_FOUND -> ErrorType.Network.NotFound
        else -> ErrorType.Network.ServerError
    }

fun handleApiExceptions(exception: Exception) =
    when (exception) {
        is SocketTimeoutException -> ErrorType.Network.Timeout
        is ConnectException, is UnknownHostException -> ErrorType.Network.NoConnection
        is IOException -> ErrorType.Network.BadRequest
        else -> ErrorType.Network.ServerError
    }
