package dev.carlodips.albertsoncodingassignment.model.repository

import dev.carlodips.albertsoncodingassignment.api.NetworkResult
import dev.carlodips.albertsoncodingassignment.model.remote_source.UsersRemoteDataSource
import dev.carlodips.albertsoncodingassignment.utils.MockData
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`


// TODO: For some reason, The tests only run when running testDebugUnitTest in Gradle tab
class RandomUsersRepositoryImplTest {

    private lateinit var remoteDataSource: UsersRemoteDataSource /*= mock()*/
    private lateinit var repo: RandomUsersRepositoryImpl

    @Before
    fun setup() {
        remoteDataSource = mock()
        repo = RandomUsersRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Test
    fun test_network_request_success() = runTest {

        val numberOfUsers = 2

        // Mock Response
        `when`(remoteDataSource.getRandomUsers(numberOfUsers))
            .thenReturn(
                NetworkResult.Success(
                    code = 200, data = MockData.getSearchMockResponse(numberOfUsers)
                )
            )

        val response = repo.getRandomUsers(numberOfUsers) as NetworkResult.Success

        assertEquals(response.data.results?.size, numberOfUsers)
    }

    @Test
    fun test_network_request_error() = runTest {

        val numberOfUsers = 2
        val error = "Something went wrong"

        // Mock Response
        `when`(remoteDataSource.getRandomUsers(numberOfUsers))
            .thenReturn(NetworkResult.Error(code = 400, errorMsg = error))

        val response = repo.getRandomUsers(numberOfUsers) as NetworkResult.Error

        assertEquals(response.errorMsg, error)

    }

    @Test
    fun test_caching_listRandomUsers_after_network_success() = runTest {
        val numberOfUsers = 5

        `when`(remoteDataSource.getRandomUsers(numberOfUsers))
            .thenReturn(
                NetworkResult.Success(
                    code = 200, data = MockData.getSearchMockResponse(numberOfUsers)
                )
            )

        repo.getRandomUsers(numberOfUsers)

        assert(repo.listRandomUsers?.size == numberOfUsers)
    }

    @Test
    fun test_find_user_by_index() = runTest {
        val numberOfUsers = 5

        `when`(remoteDataSource.getRandomUsers(numberOfUsers))
            .thenReturn(
                NetworkResult.Success(
                    code = 200, data = MockData.getSearchMockResponse(numberOfUsers)
                )
            )

        repo.getRandomUsers(numberOfUsers)

        val user = repo.findUserByIndex(3)

        assert(user != null)
    }
}
