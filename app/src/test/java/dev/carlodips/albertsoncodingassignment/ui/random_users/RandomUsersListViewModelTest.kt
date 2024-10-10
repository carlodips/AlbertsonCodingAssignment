package dev.carlodips.albertsoncodingassignment.ui.random_users

import androidx.lifecycle.SavedStateHandle
import dev.carlodips.albertsoncodingassignment.RandomUsersApplication
import dev.carlodips.albertsoncodingassignment.model.repository.RandomUsersRepository
import dev.carlodips.albertsoncodingassignment.utils.Constants
import org.junit.Before
import org.mockito.Mockito.mock

class RandomUsersListViewModelTest {
    private lateinit var vm: RandomUsersListViewModel

    @Before
    fun setup() {
        val repo: RandomUsersRepository = mock()
        val application: RandomUsersApplication = mock()
        val savedStateHandle = SavedStateHandle(mapOf(Constants.NUMBER_OF_USERS to 10))
        vm = RandomUsersListViewModel(application, repo, savedStateHandle)
    }

}