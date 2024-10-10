package dev.carlodips.albertsoncodingassignment.api

import dev.carlodips.albertsoncodingassignment.model.resp.RandomUsersResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUsersAPI {

    @GET("api/")
    suspend fun getRandomUsers(
        @Query("results") numberOfUsers: Int,
        @Query("page") page: Int? = null,
        @Query("seed") seed: String? = null
    ): Response<RandomUsersResp>
}
