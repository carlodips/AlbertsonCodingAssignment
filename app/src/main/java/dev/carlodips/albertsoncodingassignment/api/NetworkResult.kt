package dev.carlodips.albertsoncodingassignment.api

import retrofit2.HttpException
import retrofit2.Response

sealed class NetworkResult<T : Any> {
    class Success<T: Any>(val code: Int,val data: T) : NetworkResult<T>()
    class Error<T: Any>(val code: Int, val errorMsg: String?) : NetworkResult<T>()
    class Exception<T: Any>(val e: Throwable) : NetworkResult<T>()

    companion object {
        suspend fun <T : Any> handleApi(
            execute: suspend () -> Response<T>
        ): NetworkResult<T> {
            return try {
                val response = execute()

                if (response.isSuccessful) {
                    NetworkResult.Success(response.code(), response.body()!!)
                } else {
                    NetworkResult.Error(response.code(), response.errorBody()?.string())
                }
            } catch (e: HttpException) {
                NetworkResult.Error(e.code(), e.message())
            } catch (e: Throwable) {
                NetworkResult.Exception(e)
            }
        }
    }
}