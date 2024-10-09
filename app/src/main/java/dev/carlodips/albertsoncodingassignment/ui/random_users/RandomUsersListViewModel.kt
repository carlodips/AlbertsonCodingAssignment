package dev.carlodips.albertsoncodingassignment.ui.random_users

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.carlodips.albertsoncodingassignment.api.NetworkResult
import dev.carlodips.albertsoncodingassignment.model.data.RandomUser
import dev.carlodips.albertsoncodingassignment.model.repository.RandomUsersRepository
import dev.carlodips.albertsoncodingassignment.utils.Constants
import dev.carlodips.albertsoncodingassignment.utils.Event
import dev.carlodips.albertsoncodingassignment.utils.toEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RandomUsersListViewModel @Inject constructor(
    private val randomUsersRepository: RandomUsersRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val numberOfUsers = savedStateHandle.get<Int>(Constants.NUMBER_OF_USERS)

    val ldNavigateToDetails = MutableLiveData<Event<RandomUser>>()

    val ldShowToast = MutableLiveData<String>()

    private val _uiState =
        MutableStateFlow<RandomUsersListUIState>(RandomUsersListUIState.Loading)
    val uiState: StateFlow<RandomUsersListUIState>
        get() = _uiState.asStateFlow()

    init {
        getRandomUsers()
    }

    private var job: Job? = null

    private fun getRandomUsers() {
        if (numberOfUsers == null) return

        if (job?.isActive == true) return

        job = viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _uiState.update {
                    RandomUsersListUIState.Loading
                }
            }

            withContext(Dispatchers.IO) {
                val resp = randomUsersRepository.getRandomUsers(numberOfUsers = numberOfUsers)

                when (resp) {
                    is NetworkResult.Success -> {
                        _uiState.update {
                            RandomUsersListUIState.Success(
                                listUsers = resp.data.results.toMutableStateList(),
                                navigateToDetails = {
                                    ldNavigateToDetails.value = it.toEvent()
                                }
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        ldShowToast.value = resp.errorMsg ?: "Network Error"
                    }
                    is NetworkResult.Exception -> {
                        ldShowToast.value = resp.e.message
                    }
                }
            }
        }
    }
}
