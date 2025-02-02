package com.viktoriagavrosh.englishsimulator.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * Function for Repositories.
 *
 * @param getFlow function to get result from repository
 * @param mapper for converting type of result
 * @return result flow
 */
fun <T, S> getRequestResultFlow(
    getFlow: () -> Flow<List<T>>,
    mapper: (T) -> S
): Flow<RequestResult<List<S>>> {
    return try {
        getFlow()
            .map { list ->
                list.map { mapper(it) }
            }
            .map<List<S>, RequestResult<List<S>>> { RequestResult.Success(it) }
    } catch (e: Exception) {
        flow { emit(RequestResult.Error()) }
    }
}
