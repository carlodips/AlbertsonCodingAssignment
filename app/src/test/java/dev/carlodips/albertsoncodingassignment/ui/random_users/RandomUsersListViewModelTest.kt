package dev.carlodips.albertsoncodingassignment.ui.random_users

import androidx.lifecycle.SavedStateHandle
import dev.carlodips.albertsoncodingassignment.model.repository.RandomUsersRepository
import dev.carlodips.albertsoncodingassignment.utils.Constants
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class RandomUsersListViewModelTest {
    lateinit var vm: RandomUsersListViewModel

    @Before
    fun setup() {
        val repo: RandomUsersRepository = mock()
        val savedStateHandle = SavedStateHandle(mapOf(Constants.NUMBER_OF_USERS to 10))
        vm = RandomUsersListViewModel(repo, savedStateHandle)
    }

    @Test
    fun test_number_of_users_has_value() {
        vm.uiState.value
    }
}