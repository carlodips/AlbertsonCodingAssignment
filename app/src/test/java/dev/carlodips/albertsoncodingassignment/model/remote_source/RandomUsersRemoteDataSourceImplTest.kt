package dev.carlodips.albertsoncodingassignment.model.remote_source

import dev.carlodips.albertsoncodingassignment.api.RandomUsersAPI
import dev.carlodips.albertsoncodingassignment.utils.MockData
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response

class RandomUsersRemoteDataSourceImplTest {
    private val api: RandomUsersAPI = mock()

    @Test
    fun test_success() = runTest {
        val numberOfUsers = 2

        `when`(api.getRandomUsers(numberOfUsers))
            .thenReturn(Response.success(200, MockData.getSearchMockResponse(numberOfUsers)))

        val response = api.getRandomUsers(numberOfUsers)

        assertEquals(
            MockData.getSearchMockResponse(numberOfUsers).results,
            response.body()?.results
        )
    }
}
