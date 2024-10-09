package dev.carlodips.albertsoncodingassignment.model.remote_source

import dev.carlodips.albertsoncodingassignment.api.NetworkResult
import dev.carlodips.albertsoncodingassignment.model.resp.RandomUsersResp

interface RandomUsersRemoteDataSource {
    suspend fun getRandomUsers(numberOfUsers: Int): NetworkResult<RandomUsersResp>
}