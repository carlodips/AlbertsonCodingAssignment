package dev.carlodips.albertsoncodingassignment.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.carlodips.albertsoncodingassignment.model.data.RandomUser
import dev.carlodips.albertsoncodingassignment.model.repository.RandomUsersRepository
import dev.carlodips.albertsoncodingassignment.utils.Constants
import javax.inject.Inject

@HiltViewModel
class RandomUserDetailsViewModel @Inject constructor(
    private val randomUsersRepository: RandomUsersRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val index = savedStateHandle.get<Int>(Constants.USER_INDEX)

    val ldRandomUserBean = MutableLiveData<RandomUser>()


    init {
        ldRandomUserBean.value = index?.let { randomUsersRepository.findUserByIndex(it) }

        ldRandomUserBean.value
    }

}
