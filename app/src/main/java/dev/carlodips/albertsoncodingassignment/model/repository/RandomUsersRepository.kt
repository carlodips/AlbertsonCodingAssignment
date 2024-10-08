package dev.carlodips.albertsoncodingassignment.model.repository

import dev.carlodips.albertsoncodingassignment.model.data.RandomUsersResp
import retrofit2.Response

interface RandomUsersRepository {
    suspend fun getRandomUsers(numberOfUsers: Int): Response<RandomUsersResp>
}