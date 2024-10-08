package dev.carlodips.albertsoncodingassignment.api

import dev.carlodips.albertsoncodingassignment.model.data.RandomUsersResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RandomUsersAPI {

    @GET("api/")
    suspend fun getRandomUsers(
        @Query("results") numberOfUsers: Int
    ): Response<RandomUsersResp>
}
