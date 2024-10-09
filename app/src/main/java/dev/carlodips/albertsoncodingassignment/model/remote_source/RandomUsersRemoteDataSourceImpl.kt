package dev.carlodips.albertsoncodingassignment.model.remote_source

import dev.carlodips.albertsoncodingassignment.api.NetworkResult
import dev.carlodips.albertsoncodingassignment.api.RandomUsersAPI
import dev.carlodips.albertsoncodingassignment.model.resp.RandomUsersResp
import javax.inject.Inject

class RandomUsersRemoteDataSourceImpl @Inject constructor(
    private val api: RandomUsersAPI
): RandomUsersRemoteDataSource {
    override suspend fun getRandomUsers(numberOfUsers: Int): NetworkResult<RandomUsersResp> {
        val result = NetworkResult.handleApi {
            api.getRandomUsers(numberOfUsers)
        }
        return result
    }
}