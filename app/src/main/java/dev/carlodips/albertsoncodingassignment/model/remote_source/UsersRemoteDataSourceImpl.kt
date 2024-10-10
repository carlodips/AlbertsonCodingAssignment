package dev.carlodips.albertsoncodingassignment.model.remote_source

import dev.carlodips.albertsoncodingassignment.api.NetworkResult
import dev.carlodips.albertsoncodingassignment.api.RandomUsersAPI
import dev.carlodips.albertsoncodingassignment.model.resp.RandomUsersResp

class UsersRemoteDataSourceImpl (
    private val api: RandomUsersAPI
): UsersRemoteDataSource {
    override suspend fun getRandomUsers(numberOfUsers: Int): NetworkResult<RandomUsersResp> {
        val result = NetworkResult.handleApi {
            api.getRandomUsers(numberOfUsers)
        }
        return result
    }
}
