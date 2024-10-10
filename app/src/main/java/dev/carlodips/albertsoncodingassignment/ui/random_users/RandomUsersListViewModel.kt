package dev.carlodips.albertsoncodingassignment.ui.random_users

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.carlodips.albertsoncodingassignment.R
import dev.carlodips.albertsoncodingassignment.RandomUsersApplication
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
import okio.IOException
import javax.inject.Inject

@HiltViewModel
class RandomUsersListViewModel @Inject constructor(
    private val app: Application,
    private val randomUsersRepository: RandomUsersRepository,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(app) {
    private val numberOfUsers = savedStateHandle.get<Int>(Constants.NUMBER_OF_USERS)

    val ldNavigateToDetails = MutableLiveData<Event<Int>>()

    private val _uiState =
        MutableStateFlow<RandomUsersListUIState>(RandomUsersListUIState.Loading)
    val uiState: StateFlow<RandomUsersListUIState>
        get() = _uiState.asStateFlow()

    // TODO: Implement pagination?
    val pageNo = 1
    val seed = "abc"

    init {
        prepare()
    }

    private var job: Job? = null

    private fun prepare() {
        try {
            if (hasInternetConnection()) {
                getRandomUsers()
            } else {
                setError(app.getString(R.string.msg_no_internet_connection))
            }
        } catch (t: Throwable) {
            when(t) {
                is IOException -> {
                    setError(app.getString(R.string.msg_network_failure))
                }
                else -> {
                    setError(app.getString(R.string.msg_conversion_error))
                }
            }
        }
    }


    private fun getRandomUsers() {
        if (numberOfUsers == null) return

        if (job?.isActive == true) return

        job = viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _uiState.update {
                    RandomUsersListUIState.Loading
                }
            }

            val resp = withContext(Dispatchers.IO) {
                randomUsersRepository.getRandomUsers(
                    numberOfUsers = numberOfUsers/*,
                    pageNo = pageNo,
                    seed = seed*/
                )
            }

            withContext(Dispatchers.Main) {
                when (resp) {
                    is NetworkResult.Success -> {
                        if (resp.data.results == null) return@withContext

                        _uiState.update {
                            RandomUsersListUIState.Success(
                                input = numberOfUsers.toString(),
                                listUsers = resp.data.results.toMutableStateList(),
                                navigateToDetails = {
                                    ldNavigateToDetails.value = it.toEvent()
                                }
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        RandomUsersListUIState.Error(
                            resp.errorMsg ?: app.getString(R.string.msg_network_error)
                        )
                    }

                    is NetworkResult.Exception -> {
                        RandomUsersListUIState.Error(
                            resp.e.message ?: app.getString(R.string.msg_network_error)
                        )

                    }
                }
            }
        }
    }

    private fun setError(errorMessage: String) {
        _uiState.update {
            RandomUsersListUIState.Error(errorMessage)
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<RandomUsersApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            capabilities.hasTransport(TRANSPORT_WIFI) || capabilities.hasTransport(
                TRANSPORT_CELLULAR
            ) || capabilities.hasTransport(TRANSPORT_ETHERNET) -> true

            else -> false
        }
    }
}
