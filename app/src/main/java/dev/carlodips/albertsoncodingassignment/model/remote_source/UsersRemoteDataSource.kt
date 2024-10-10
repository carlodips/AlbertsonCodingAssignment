package dev.carlodips.albertsoncodingassignment.model.remote_source

import dev.carlodips.albertsoncodingassignment.api.NetworkResult
import dev.carlodips.albertsoncodingassignment.model.resp.RandomUsersResp

interface UsersRemoteDataSource {
    suspend fun getRandomUsers(
        numberOfUsers: Int,
        pageNo: Int? = null,
        seed: String? = null
    ): NetworkResult<RandomUsersResp>
}
