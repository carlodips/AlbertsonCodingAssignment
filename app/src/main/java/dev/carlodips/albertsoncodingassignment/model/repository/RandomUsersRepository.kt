package dev.carlodips.albertsoncodingassignment.model.repository

import dev.carlodips.albertsoncodingassignment.model.resp.RandomUsersResp
import dev.carlodips.albertsoncodingassignment.api.NetworkResult
import dev.carlodips.albertsoncodingassignment.model.data.RandomUser

interface RandomUsersRepository {
    suspend fun getRandomUsers(numberOfUsers: Int): NetworkResult<RandomUsersResp>
    fun findUserByIndex(index: Int): RandomUser?
}