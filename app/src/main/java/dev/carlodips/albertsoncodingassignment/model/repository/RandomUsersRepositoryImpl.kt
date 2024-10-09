package dev.carlodips.albertsoncodingassignment.model.repository

import dev.carlodips.albertsoncodingassignment.api.NetworkResult
import dev.carlodips.albertsoncodingassignment.api.RandomUsersAPI
import dev.carlodips.albertsoncodingassignment.model.data.RandomUser
import dev.carlodips.albertsoncodingassignment.model.resp.RandomUsersResp
import javax.inject.Singleton

class RandomUsersRepositoryImpl(
    private val api: RandomUsersAPI
) : RandomUsersRepository {

    var listRandomUsers: ArrayList<RandomUser>? = null
        private set

    override suspend fun getRandomUsers(numberOfUsers: Int): NetworkResult<RandomUsersResp> {
        val result = NetworkResult.handleApi {
            api.getRandomUsers(numberOfUsers)
        }

        if (result is NetworkResult.Success) {
            listRandomUsers = ArrayList(result.data.results)
        }

        return result
    }

    override fun findUserByIndex(index: Int): RandomUser? {
        return listRandomUsers?.get(index)
    }

    fun clearList() {
        listRandomUsers = null
    }
}
