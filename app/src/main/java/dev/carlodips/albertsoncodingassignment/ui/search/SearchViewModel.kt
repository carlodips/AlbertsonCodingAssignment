package dev.carlodips.albertsoncodingassignment.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.carlodips.albertsoncodingassignment.model.repository.RandomUsersRepository
import dev.carlodips.albertsoncodingassignment.utils.Event
import dev.carlodips.albertsoncodingassignment.utils.toEvent
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val randomUsersRepository: RandomUsersRepository
): ViewModel() {

    val ldInput = MutableLiveData<String>()

    val ldNavigate = MutableLiveData<Event<String>>()

    fun onButtonClick() {
        if (ldInput.value == null) return

        ldNavigate.value = ldInput.value!!.toEvent()
    }
}