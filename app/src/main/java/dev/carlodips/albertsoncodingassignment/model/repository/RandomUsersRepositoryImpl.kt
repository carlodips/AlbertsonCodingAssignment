package dev.carlodips.albertsoncodingassignment.model.repository

import dev.carlodips.albertsoncodingassignment.api.NetworkResult
import dev.carlodips.albertsoncodingassignment.api.RandomUsersAPI
import dev.carlodips.albertsoncodingassignment.model.resp.RandomUsersResp

class RandomUsersRepositoryImpl(
    private val api: RandomUsersAPI
) : RandomUsersRepository {
    override suspend fun getRandomUsers(numberOfUsers: Int): NetworkResult<RandomUsersResp> {
        return NetworkResult.handleApi {
            api.getRandomUsers(numberOfUsers)
        }
    }
}
