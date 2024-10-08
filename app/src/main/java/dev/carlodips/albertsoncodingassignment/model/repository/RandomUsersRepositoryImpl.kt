package dev.carlodips.albertsoncodingassignment.model.repository

import dev.carlodips.albertsoncodingassignment.api.RandomUsersAPI
import dev.carlodips.albertsoncodingassignment.model.data.RandomUsersResp
import retrofit2.Response

class RandomUsersRepositoryImpl(
    private val api: RandomUsersAPI
): RandomUsersRepository {
    override suspend fun getRandomUsers(numberOfUsers: Int): Response<RandomUsersResp> {
        return api.getRandomUsers(numberOfUsers)
    }
}
