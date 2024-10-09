package dev.carlodips.albertsoncodingassignment.model.repository

import dev.carlodips.albertsoncodingassignment.model.resp.RandomUsersResp
import dev.carlodips.albertsoncodingassignment.api.NetworkResult

interface RandomUsersRepository {
    suspend fun getRandomUsers(numberOfUsers: Int): NetworkResult<RandomUsersResp>
}