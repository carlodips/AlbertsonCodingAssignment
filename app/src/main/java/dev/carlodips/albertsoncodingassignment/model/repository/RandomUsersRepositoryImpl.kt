package dev.carlodips.albertsoncodingassignment.model.repository

import dev.carlodips.albertsoncodingassignment.api.NetworkResult
import dev.carlodips.albertsoncodingassignment.model.data.RandomUser
import dev.carlodips.albertsoncodingassignment.model.remote_source.UsersRemoteDataSource
import dev.carlodips.albertsoncodingassignment.model.resp.RandomUsersResp

class RandomUsersRepositoryImpl(
    private val remoteDataSource: UsersRemoteDataSource
) : RandomUsersRepository {

    var listRandomUsers: ArrayList<RandomUser>? = null
        private set

    override suspend fun getRandomUsers(
        numberOfUsers: Int,
        pageNo: Int?,
        seed: String?
    ): NetworkResult<RandomUsersResp> {
        val result = remoteDataSource.getRandomUsers(
            numberOfUsers = numberOfUsers,
            pageNo = pageNo,
            seed = seed
        )

        if (result is NetworkResult.Success) {
            listRandomUsers = result.data.results?.let { ArrayList(it) }
        }

        return result
    }

    override fun findUserByIndex(index: Int): RandomUser? {
        return listRandomUsers?.get(index)
    }
}
