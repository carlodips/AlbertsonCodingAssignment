package dev.carlodips.albertsoncodingassignment.api

import dev.carlodips.albertsoncodingassignment.model.RandomUsersResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RandomUsersAPI {

    @GET("api/")
    suspend fun getRandomUsers(
        @QueryMap map: Map<String, String>
    ): Response<RandomUsersResp>
}
